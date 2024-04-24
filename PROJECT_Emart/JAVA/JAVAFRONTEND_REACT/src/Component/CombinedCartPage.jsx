import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ListGroup, Button,Col,Badge,Row,Container } from 'react-bootstrap';
import './CombinedcartPage.css'; 

const CombinedCartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [productDetails, setProductDetails] = useState({});
  const [isCardHolder, setIsCardHolder] = useState(false);
  const [points, setPoints] = useState(0);
  const [isRedeemingPoints, setIsRedeemingPoints] = useState(false);
  const [isButtonClicked, setIsButtonClicked] = useState(false);
  const [totalAmount, setTotalAmount] = useState(0);
  const maxPoints = 100; 

  const navigate = useNavigate();

  const fetchCartItemsFun = async () => {
    try {
      const response = await fetch(`http://localhost:8080/Cart/cust/` + sessionStorage.getItem('custId'));
      const data = await response.json();
      if (Array.isArray(data)) {
        setCartItems(data);
        calculateTotalAmount(data);
      } else {
        console.error('Expected an array but received:', data);
      }
    } catch (error) {
      console.error('Error fetching cart items:', error);
    }
  };

  useEffect(() => {
    console.log('islogin:', sessionStorage.getItem('islogin'));
  
    if (sessionStorage.getItem('islogin') !== 'true') {
      console.log('User is not logged in. Redirecting to login.');
      navigate(`/login`);
      return;
    }

    fetchCartItemsFun();

    fetch(`http://localhost:8080/Customer/isCardHolder/` + sessionStorage.getItem('custId'))
      .then((res) => res.json())
      .then((data) => {
        setIsCardHolder(data);
      })
      .catch((error) => {
        console.error('Error fetching isCardHolder status:', error);
      });
  }, [navigate]);

  useEffect(() => {
    if (isCardHolder) {
      fetch(`http://localhost:8080/Customer/points/` + sessionStorage.getItem('custId'))
        .then((res) => res.json())
        .then((data) => {
          setPoints(data);
        })
        .catch((error) => {
          console.error('Error fetching isCardHolder status:', error);
        });

      const productIDs = [...new Set(cartItems.map((item) => item.prodID))];
      fetchProductDetails(productIDs);
    }
  }, [cartItems, isCardHolder]);



  const fetchProductDetails = async (productIDs) => {
    const productDetailsFetchPromises = productIDs.map((id) =>
      fetch(`http://localhost:8080/product/${id}`)
        .then((response) => response.json())
        .then((data) => ({ id, details: data }))
    );
   

    try {
      const detailsData = await Promise.all(productDetailsFetchPromises);
      const detailsObject = detailsData.reduce((obj, { id, details }) => {
        obj[id] = details;
        return obj;
      }, {});
      setProductDetails(detailsObject);
    } catch (error) {
      console.error('Error fetching product details:', error);
    }
  };

  const handleIncrement = (index) => {
    const updatedCartItems = [...cartItems];
    updatedCartItems[index].qty += 1;
    setCartItems(updatedCartItems);
    updateCartItemQuantity(updatedCartItems[index].cartID, updatedCartItems[index].qty);
  };
  
  const handleDecrement = (index) => {
    const updatedCartItems = [...cartItems];
    if (updatedCartItems[index].qty > 1) {
      updatedCartItems[index].qty -= 1;
      setCartItems(updatedCartItems);
      updateCartItemQuantity(updatedCartItems[index].cartID, updatedCartItems[index].qty);
    }
  };
  const updateCustomerPoints = async (newPoints) => {
    try {
      await fetch(`http://localhost:8080/Customer/points/${sessionStorage.getItem('custId')}/${newPoints}`, {
        method: 'PUT',
      });
      console.log('Customer points updated successfully');
    } catch (error) {
      console.error('Error updating customer points:', error);
    }
  };
  const updateCartItemQuantity = async (cartItemId, newQuantity) => {
    try {
      
      await fetch(`http://localhost:8080/Cart/${newQuantity}/${cartItemId}`, {
        method: 'PUT',
      });
      fetchCartItemsFun();
    } catch (error) {
      console.error('Error updating quantity:', error);
    }
  };
  const calculateTotalAmount = (items) => {
    let totalAmt = 0;
    items.forEach((item) => {
  const product = productDetails[item.prodID];
  console.log('product:', product);
  console.log('item.qty:', item.qty);
  if (product) {
    totalAmt +=
      isCardHolder ?
      (product.cardHolderPrice || product.offerPrice) * item.qty :
      product.offerPrice === 0 ?
      product.mrpPrice * item.qty :
      product.offerPrice * item.qty;
  }
});
console.log('totalAmt:', totalAmt);
    if (setIsRedeemingPoints) {
      totalAmt -= points; 
    }
    setTotalAmount(totalAmt);
  };

  const handleRemove = (cartItemId) => {
    setCartItems(cartItems.filter((item) => item.cartID !== cartItemId));
    fetch(`http://localhost:8080/Cart/${cartItemId}`, {
      method: 'DELETE',
    });
  };
  const deleteCartItems = async () => {
    const deletePromises = cartItems.map((item) =>
      fetch(`http://localhost:8080/Cart/${item.cartID}`, {
        method: 'DELETE',
      })
    );
  
    try {
      await Promise.all(deletePromises);
      console.log('All cart items deleted successfully');
      setCartItems([]); // Clear the cart items in the state
    } catch (error) {
      console.error('Error deleting cart items:', error);
    }
  };

  const placeOrder = () => {
    deleteCartItems();
    let TotalAmount=0;
    if(isRedeemingPoints) {
      TotalAmount = totalAmount - points
    }else {
      TotalAmount = totalAmount;
    }
   

    const invoiceData = {
      
      totalAmt: TotalAmount / 1.18,   
      tax: (TotalAmount / 1.18) * 0.18,
      deliveryCharge: 100,
      custID: parseInt(sessionStorage.getItem("custId")),
      invoiceDate: new Date().toISOString().split('T')[0], 
      totalBill: TotalAmount + 100, 
    };
    

    fetch('http://localhost:8080/invoice', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(invoiceData),
      })
      .then((response) => response.json())
      .then((data) => {
        console.log('Invoice created successfully:', data);

        cartItems.forEach((item) => {
          const invoiceDetails = {
            mrp: productDetails[item.prodID]?.mrpPrice || 0,
            invoiceID: data.invoiceID,
            prodID: item.prodID,
            pointsRedeem: productDetails[item.prodID]?.pointsRedeem || 0,
            cardHolderPrice: productDetails[item.prodID]?.cardHolderPrice || 0,
            prodName: productDetails[item.prodID]?.prodName || 0,
          };

          fetch('http://localhost:8080/Invoicedetails', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: JSON.stringify(invoiceDetails),
            })
            .then((response) => response.json())
            .then((detailData) => {
              console.log('Invoice detail created successfully:', detailData);
            })
            .catch((error) => {
              console.error('Error creating invoice detail:', error);
            });

        
        });
      })
      .catch((error) => {
        console.error('Error creating invoice:', error);
      });
      

    setIsButtonClicked(true);
    const timeoutId = setTimeout(() => {
      navigate('/order');
    }, 1500);
  };

  const handlecheck = (e, prodid, prodPoints, prodDisc) => {
    const finalPrice = productDetails[prodid]?.cardHolderPrice;
    console.log('points:', points);
    console.log('prodPoint:', prodPoints);
    
    if (e.target.checked) {
      if (points >= prodPoints) {
        const newPoints = points - prodPoints;
        setTotalAmount(totalAmount - prodPoints);
        setPoints(newPoints);
        updateCustomerPoints(newPoints); // Update customer points
      } else {
        window.alert("You don't have enough points");
        e.target.checked = false;
      }
    } else {
      if (points + prodPoints <= maxPoints) {
        const newPoints = points + prodPoints;
        setPoints(newPoints);
        updateCustomerPoints(newPoints); // Update customer points
      }
      setTotalAmount(totalAmount + prodPoints);
    }
  };
  
  useEffect (() => { 
    if(productDetails){
      
  calculateTotalAmount(cartItems)
    }
  },[productDetails])
  useEffect(() => {
    if (productDetails) {
      calculateTotalAmount(cartItems);
    }
  }, [productDetails, cartItems]);

  const renderCartItem = (item, index) => {
    return (
      <ListGroup.Item key={item.cartID}>
        <Row>
          <Col>Product ID: {item.prodID}</Col>
          <Col>Product Name: {productDetails[item.prodID]?.prodName}</Col>
          <Col>Quantity in Cart: {item.qty}</Col>
          <Col>
            <Button variant="outline-success" onClick={() => handleIncrement(index)}>+</Button>{' '}
            <Button variant="outline-danger" onClick={() => handleDecrement(index)}>-</Button>{' '}
            <Button variant="outline-dark" onClick={() => handleRemove(item.cartID)}>Remove</Button>
          </Col>
          <Col className="d-flex align-items-center">
  <label className="mr-2">Use Super Coins</label>
  
  <input type="checkbox" onChange={(e) => handlecheck(e, item.prodID, productDetails[item.prodID]?.pointsRedeem, productDetails[item.prodID]?.offerPrice
)} />
       </Col>
        </Row>
      </ListGroup.Item>
    );
  };

  return (
    <Container className="mt-5">
      <Row>
        <Col>
          <h2 className="text-center mb-4">Cart Page</h2>
          <Badge variant="info">Points: {points}</Badge>
          <ListGroup className="mb-4">{cartItems.map((item, index) => renderCartItem(item, index))}</ListGroup>
          <p className="text-right">Total Cart Amount: ₹{totalAmount && totalAmount}</p>
          {cartItems.length > 0 && (
            <Button variant="primary" className={`place-order-button ${isButtonClicked ? 'clicked' : ''}`} onClick={placeOrder}>
              Place Order
            </Button>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default CombinedCartPage;
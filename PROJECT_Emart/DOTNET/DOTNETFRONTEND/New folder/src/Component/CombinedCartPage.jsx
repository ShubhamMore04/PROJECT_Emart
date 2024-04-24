import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ListGroup, Button,Col,Badge,Row,Container } from 'react-bootstrap';
import './CombinedcartPage.css'; 

const CombinedCartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [productDetails, setProductDetails] = useState({});
  const [isCardHolder, setIsCardHolder] = useState(false);
  const [points, setPoints] = useState(0);
  const [isButtonClicked, setIsButtonClicked] = useState(false);
  const [totalAmount, setTotalAmount] = useState(0);
  const [checkedItems, setCheckedItems] = useState({});
  const [newPoints, setnewPoints] = useState(0);
  const navigate = useNavigate();

  const fetchCartItemsFun = async () => {
    try {
      const response = await fetch(`https://localhost:7040/Cart/cust/` + sessionStorage.getItem('custId'));
      console.log(response);
      const data = await response.json();
      console.log(data);
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

    fetch(`https://localhost:7040/Customer/isCardHolder/` + sessionStorage.getItem('custId'))
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
      fetch(`https://localhost:7040/Customer/points/${sessionStorage.getItem('custId')}` )
        .then((res) => res.json())
        .then((data) => {
          setnewPoints(data);
          setPoints(data)
        })
        .catch((error) => {
          console.error('Error fetching isCardHolder status:', error);
        });

      const productIDs = [...new Set(cartItems.map((item) => item.prodid))];
      console.log('productIDs:', productIDs);
      fetchProductDetails(productIDs);
    }
  }, [cartItems, isCardHolder]);



  const fetchProductDetails = async (productIDs) => {
    const productDetailsFetchPromises = productIDs.map((id) =>
      fetch(`https://localhost:7040/api/ProductMaster/${id}`)
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
    updateCartItemQuantity(updatedCartItems[index].cartId, updatedCartItems[index].qty);
  };
  
  const handleDecrement = (index) => {
    const updatedCartItems = [...cartItems];
    if (updatedCartItems[index].qty > 1) {
      updatedCartItems[index].qty -= 1;
      setCartItems(updatedCartItems);
      updateCartItemQuantity(updatedCartItems[index].cartId, updatedCartItems[index].qty);
    }
  };
  const updateCustomerPoints = async (newPoints1) => {
    try {
      await fetch(`https://localhost:7040/Customer/points/${sessionStorage.getItem('custId')}/${newPoints1}`, {
        method: 'PUT',
      });
      console.log('Customer points updated successfully');
    } catch (error) {
      console.error('Error updating customer points:', error);
    }
  };
  const updateCartItemQuantity = async (cartItemId, newQuantity) => {
    try {
      
      await fetch(`https://localhost:7040/Cart/${newQuantity}/${cartItemId}`, {
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
      const product = productDetails[item.prodid];
      if (product) {
        if (isCardHolder && checkedItems[product.id] && product.pointsRedeem > 0) {
          totalAmt += (product.offerPrice - product.pointsRedeem) * item.qty;
        } else if (isCardHolder && product.pointsRedeem==0) {
          totalAmt += product.offerPrice * item.qty;
        } else {
          totalAmt += product.offerPrice * item.qty;
        }
      }
    });
    setTotalAmount(totalAmt);
  };
  
  
  

  const handleRemove = (cartItemId) => {
    setCartItems(cartItems.filter((item) => item.cartId !== cartItemId));
    fetch(`https://localhost:7040/Cart/${cartItemId}`, {
      method: 'DELETE',
    });
  };
  const deleteCartItems = async () => {
    const deletePromises = cartItems.map((item) =>
      fetch(`https://localhost:7040/Cart/${item.cartId}`, {
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
    updateCustomerPoints(newPoints); 
    
  
    const invoiceData = {
      totalAmt: totalAmount,
      tax: totalAmount * 0.18,
      deliveryCharge: 100,
      custId: parseInt(sessionStorage.getItem("custId")),
      invoiceDate: new Date().toISOString().split('T')[0],
      totalBill: totalAmount+(totalAmount * 0.18) + 100,
    };
  
    fetch('https://localhost:7040/invoice', {
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
          mrp: productDetails[item.prodid]?.mrpPrice || 0,
          invoiceid: data.invoiceId,
          prodid: item.prodid,
          pointsRedeem: productDetails[item.prodid]?.pointsRedeem || 0,
          cardHolderPrice: productDetails[item.prodid]?.cardHolderPrice || 0,
          prodName: productDetails[item.prodid]?.prodName || 0,
        };
  
        fetch('https://localhost:7040/Invoicedetails', {
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
  
      setIsButtonClicked(true);
      const timeoutId = setTimeout(() => {
        navigate(`/order/${data.invoiceId}`);
      }, 1500);
    })
    .catch((error) => {
      console.error('Error creating invoice:', error);
    });
  };
  
  const handlecheck = (e, prodid, prodPoints, prodDisc) => {
    
    
    if (!isCardHolder) {
      window.alert("Emart Says : Only cardholders can redeem points");
      e.target.checked = false;
      return;
    }

    setCheckedItems({ ...checkedItems, [prodid]: e.target.checked });
    const finalPrice = productDetails[prodid]?.offerPrice; 
    

    if (e.target.checked) {
     if (newPoints >= prodPoints) {
        setnewPoints(newPoints - prodPoints);
        setTotalAmount(totalAmount - prodPoints); // Deduct points from offerPrice
        // Update customer points
      } else {
        window.alert("Emart Says : You don't have enough points");
        e.target.checked = false;
      }

   } else {
      if (!e.target.checked) { // Check if the item was previously checked
        setnewPoints(newPoints+prodPoints); // Only update if the item was previously checked
        setTotalAmount(totalAmount + prodPoints); // Add points to offerPrice
      }

    }
  };
  
  useEffect(() => {
    if (cartItems.length > 0) {
      const productIDs = [...new Set(cartItems.map((item) => item.prodid))];
      console.log('productIDs:', productIDs);
      fetchProductDetails(productIDs);
    }
  }, [cartItems]);
  useEffect(() => {
    if (productDetails && cartItems.length > 0) {
      calculateTotalAmount(cartItems);
    }
  }, [productDetails, cartItems]);

  const renderCartItem = (item, index) => {
    return (
      <ListGroup.Item key={item.cartId}>
        <Row>
          <Col>Product ID: {item.prodid}</Col>
          <Col>Product Name: {productDetails[item.prodid]?.prodName}</Col>
          <Col>Quantity in Cart: {item.qty}</Col>
          <Col>Offer Price: ₹{productDetails[item.prodid]?.offerPrice}</Col>
          <Col>
            <Button variant="outline-success" onClick={() => handleIncrement(index)}>+</Button>{' '}
            <Button variant="outline-danger" onClick={() => handleDecrement(index)}>-</Button>{' '}
            <Button variant="outline-dark" onClick={() => handleRemove(item.cartId)}>Remove</Button>
            </Col>
            {console.log(isCardHolder)}
        {isCardHolder && (
          <Col className="d-flex align-items-center">
            <label className="mr-2">Coins</label>
            <input type="checkbox" onChange={(e) => handlecheck(e, item.prodid, productDetails[item.prodid]?.pointsRedeem, productDetails[item.prodid]?.offerPrice)} />
          </Col>
        )}
        </Row>
      </ListGroup.Item>
    );
  };

  return (
    <Container className="mt-5">
      <Row>
        <Col>
          <h2 className="text-center mb-4">Cart Page</h2>
          <Badge variant="info">Points: {newPoints}</Badge>
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
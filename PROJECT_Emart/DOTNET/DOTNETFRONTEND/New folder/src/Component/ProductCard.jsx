import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Card, Button, Alert, Row, Col } from 'react-bootstrap'; // Import Bootstrap components
import { useNavigate } from 'react-router-dom';
import "./ProductCard.css";

const ProductCard = ({
  id,
  imgpath,
  prodName,
  prodDisc,
  prodPoints,
  prodLongDesc,
  prodShortDesc,
  cardHolderPrice,
  offerPrice,
  mrpPrice
}) => {
  const navigate = useNavigate();
  const [cart, setCart] = useState({
    prodId: "",
    custId: "",
    qty: ""
  });

  const [showLongDesc, setShowLongDesc] = useState(false);

  const handleAddToCart = (id) => {
    console.log(id);
    if (sessionStorage.getItem("islogin") === "true") {
      setCart({
        prodId: id,
        custId: sessionStorage.getItem("custId"),
        qty: 1

      });
      
      
      return;
    }

    navigate('/login');
  };

  useEffect(() => {
    if (cart.prodId && cart.custId) {
      
      fetch("https://localhost:7040/Cart", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cart)
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Cart data couldn't be sent.");
          }
          alert("Emart Says : Cart data sent successfully.");
        })
        .catch((error) => {
          alert("Emart Says : Item Already added in the cart");
          
        });
    }
  }, [cart]);
  

  const toggleLongDesc = () => {
    setShowLongDesc(!showLongDesc);
  };

  return (
    <Row>
      <Col md={4}>
        <Card style={{ width: '18rem', marginBottom: '20px' }}>
          <Card.Img variant="top" src={imgpath} alt={prodName} />
          <Card.Body>
           
            {showLongDesc ? (
              <Card.Text>{prodLongDesc}</Card.Text>
            ) : (
              <Card.Text>{prodShortDesc}</Card.Text>
            )}
            <Card.Text>
              MRP - ₹{mrpPrice} <br />
              Offer Price - ₹{offerPrice} <br />
              CardHolderOfferPrice - ₹{cardHolderPrice} <br />
              {prodPoints != 0 && `Points - ${prodPoints}`} <br />
              {prodPoints != 0 && `Discount - ${prodDisc==0 ? "100%" : prodDisc+"%"}`}
            </Card.Text>
            {prodLongDesc && (
              <Alert variant="info" onClick={toggleLongDesc}>
                {showLongDesc ? 'Show Less' : 'Show More'}
              </Alert>
            )}
            <Button variant="primary" onClick={() => handleAddToCart(id)}>
              Add to Cart
            </Button>
          </Card.Body>
        </Card>
      </Col>
    </Row>
  );
};

ProductCard.propTypes = {
  
  id: PropTypes.number.isRequired, 
  imgpath: PropTypes.string.isRequired,
  prodName: PropTypes.string.isRequired,
  prodLongDesc: PropTypes.string.isRequired,
  prodShortDesc: PropTypes.string.isRequired,
  offerPrice: PropTypes.number.isRequired,
  mrpPrice: PropTypes.number.isRequired
};
export default ProductCard;
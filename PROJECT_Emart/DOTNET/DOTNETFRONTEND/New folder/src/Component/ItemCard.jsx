import React from 'react';
import { Card, Button } from 'react-bootstrap'; 
import './Itemcard.css';

const ItemCard = ({ title, img }) => {
  return (
    <Card className="item-card">
      <Card.Img variant="top" src={img} className="card-image" />
      <Card.Body>
        <Card.Title className="card-title">{title}</Card.Title>
        
      </Card.Body>
    </Card>
  );
};

export default ItemCard;
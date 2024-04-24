import React from 'react';
import { Card, Button, Col } from 'react-bootstrap';
import './Itemcard.css';

const ItemCard = ({ title, img }) => {
  return (
    <Col xs={12} sm={6} md={3} lg={2}>
      <Card style={{ width: '14rem', marginBottom: '20px' }}>
        <Card.Img variant="top" src={img} />
        <Card.Body>
          <Card.Title>{title}</Card.Title>
          <Button variant="primary">Move to sub categories</Button>
        </Card.Body>
      </Card>
    </Col>
  );
};

export default ItemCard;
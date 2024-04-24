import React from 'react';
import { Navbar, Nav, Container, Row, Col, Button } from 'react-bootstrap';

const AboutPage = () => {
  return (
    <>
     

      <Container className="my-4">
        <Row className="flex-lg-row-reverse align-items-center g-5 py-5 d-flex">
          <Col xs={12} sm={8} lg={6}>
            <img src="web1.jpg" alt="" className="img-fluid" width="500" height="500" loading="lazy" />
          </Col>
          <Col lg={6}>
            <h2 className="display-5 fw-bold lh-1 mb-3">A place for all people to buy</h2>
            <p className="lead">We were thinking of building a place for buying items online</p>
          </Col>
        </Row>
      </Container>

    
    </>
  );
}

export default AboutPage;

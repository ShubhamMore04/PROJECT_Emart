import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import ItemCard from './ItemCard';
import { Container, Row, Col } from 'react-bootstrap';
import { Carousel } from 'react-bootstrap'; 

const SubCategory = () => {
  const { id } = useParams();
  let navigate = useNavigate();
  const [Subcategories, setSubcategories] = useState([]);

  const handleClick = (id, flag) => {
    if (flag) {
      navigate(`/subcat/${id}`);
    } else {
      navigate(`/product/${id}`);
    }
  };

  const fetchSubcategories = async () => {
    try {
      const response = await fetch('https://localhost:7040/Category/getCatNameByParentId/' + id);
      const data = await response.json();
      setSubcategories(data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchSubcategories();
  }, [id]);

  return (

    <div><Carousel>
    <Carousel.Item>
      <img
        className="d-block w-100 carousel-image"
        src="/Images/offer_images/1242x450_NSD_n.png"
        alt="First slide"
      />
      <Carousel.Caption>
      </Carousel.Caption>
    </Carousel.Item>

    <Carousel.Item>
      <img
        className="d-block w-100 carousel-image"
        src="/Images/offer_images/MonsoonCarnival_V2_1242x450-StoreHeader.jpg"
        alt="Second slide"
      />
      <Carousel.Caption>
      </Carousel.Caption>
    </Carousel.Item>

    <Carousel.Item>
      <img
        className="d-block w-100 carousel-image"
        src="/Images/offer_images/1242x450_NSD_n.png"
        alt="Second slide"
      />
      <Carousel.Caption>
      </Carousel.Caption>
    </Carousel.Item>

    <Carousel.Item>
      <img
        className="d-block w-100 carousel-image"
        src="/Images/offer_images/MonsoonCarnival_V2_1242x450-StoreHeader.jpg"
        alt="Second slide"
      />
      <Carousel.Caption>
      </Carousel.Caption>
    </Carousel.Item>

    {/* Add more Carousel.Items for additional offer images */}
  </Carousel>

  <br></br>
    <Container>
      <Row>
        {Array.isArray(Subcategories) &&
          Subcategories.map((i) => (
            <Col md={4} key={i.catmasterId} onClick={() => handleClick(i.catmasterId, i.childflag)}>
              <ItemCard title={i.categoryName} img={i.catImgPath} />
            </Col>
          ))}
      </Row>
    </Container>
    </div>
  );
};

export default SubCategory;
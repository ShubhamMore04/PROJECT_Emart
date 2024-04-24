import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ItemCard from './ItemCard';
import { Carousel } from 'react-bootstrap'; // Import Carousel from react-bootstrap
import './CategoriesPage.css'; // Import the CSS file


const CategoriesPage = () => {
  const navigate = useNavigate();
  const [maincategories, setMaincategories] = useState([]);

  useEffect(() => {
    fetch('https://localhost:7040/Category/getCatNameByParentId/0')
      .then(response => response.json())
      .then(data => {
        setMaincategories(data);
        console.log(data);
      })
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  const handleClick = (id, flag) => {
    console.log('i-', flag);
    console.log('i-', id);
    if (flag) {
      navigate(`/subcat/${id}`);
    } else {
      navigate(`/product/${id}`);
    }
  };

  return (
    <div>
      {/* Carousel Section */}
      <Carousel>
  <Carousel.Item>
    <img
      className="d-block w-100 carousel-image"
      src="/Images/offer_images/corosel offer_2.jpg"
      alt="First slide"
    />
    <Carousel.Caption>
    </Carousel.Caption>
  </Carousel.Item>
  <Carousel.Item>
    <img
      className="d-block w-100 carousel-image"
      src="/Images/offer_images/Vijay-Sales-offer.jpeg"
      alt="Second slide"
    />
    <Carousel.Caption>
    </Carousel.Caption>
  </Carousel.Item>
  {/* Add more Carousel.Items for additional offer images */}
</Carousel>


      {/* Categories Section */}
      <div className="categories-container">
        {maincategories?.map((i) => (
          <div key={i.catmasterId} onClick={() => { handleClick(i.catmasterId, i.childflag) }} className="item-card">
<ItemCard 
  title={i.categoryName} 
  img={i.catImgPath} 
  style={{
    boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2)', 
    transition: '0.3s', 
    width: '40%', 
    borderRadius: '5px', 
    margin: '20px'
  }} 
/>          </div>
        ))}
      </div>
    </div>
  );
};

export default CategoriesPage;
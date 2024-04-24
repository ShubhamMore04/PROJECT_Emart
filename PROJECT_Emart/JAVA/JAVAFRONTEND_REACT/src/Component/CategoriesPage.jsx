// CategoriesPage.jsx

import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ItemCard from './ItemCard';

const CategoriesPage = () => {
  const navigate = useNavigate();
  const [maincategories, setMaincategories] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/Category/getCatNameByParentId/0')
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
    <div style={{ margin: '20px', display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))', gap: '20px' }}>
      {maincategories?.map((i) => (
        <div key={i.catmasterID} onClick={() => { handleClick(i.catmasterID, i.childflag) }}>
          <ItemCard title={i.categoryName} img={i.catImgPath} />
        </div>
      ))}
    </div>
  );
};

export default CategoriesPage;

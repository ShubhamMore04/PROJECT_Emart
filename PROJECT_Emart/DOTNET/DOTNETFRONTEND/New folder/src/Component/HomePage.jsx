import React, { useEffect, useState } from 'react';
import ProductCard from './ProductCard'; // Import your ProductCard component

const HomePage = () => {
  const [discountedProducts, setDiscountedProducts] = useState([]);

  useEffect(() => {
    fetch('https://localhost:7040/api/ProductMaster/discounted')
      .then(response => response.json())
      .then(data => {
        setDiscountedProducts(data);
      })
      .catch(error => console.error('Error fetching discounted products:', error));
  }, []);

  return (
    <div style={{ width: '100%', height: '100vh' }}>
      <div className="video-content">
      <video className="d-block w-100" autoPlay loop muted>
  <source src="/Images/web2.mp4" type="video/mp4" />
  Your browser does not support the video tag.
</video>
        <div style={{ padding: '20px' }}>
        <h1 style={{ fontWeight: 'bold', color: '#FF6347' }}>Our Top Discounted products</h1>      </div>
        
        {/* Render discounted products */}
        <div style={{ margin: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'space-around' }}>
          {discountedProducts?.map((i) => (
            <div key={i.prodId} style={{ padding: '10px', flex: '0 0 200px', height: '200px' }}>
              <ProductCard
                id={i.prodId}
                prodDisc={i.disc}
                prodPoints={i.pointsRedeem}
                prodName={i.prodName}
                imgpath={i.imgpath}
                prodShortDesc={i.prodShortDesc}
                cardHolderPrice={i.cardHolderPrice}
                offerPrice={i.offerPrice}
                mrpPrice={i.mrpPrice}
                prodLongDesc={i.prodLongDesc}
              />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default HomePage;
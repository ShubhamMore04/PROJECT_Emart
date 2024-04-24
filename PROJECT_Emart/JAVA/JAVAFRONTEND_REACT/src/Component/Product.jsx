import React, { useState , useEffect} from 'react'
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import ProductCard from './ProductCard';

const Product = () => {

    const [product,setProduct] = useState([]); 
    const { id } = useParams();
    let navigate = useNavigate();


    useEffect(() => {
        fetch('http://localhost:8080/product/getCatId/' + id)
          .then(response => response.json())
          .then(data => {
            setProduct(data);
            console.log(data);
          })
          .catch(error => console.error('Error fetching data:', error));
      }, [id]);

  return (
    <div style={{ margin: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'space-around' }}>
        {product?.map((i) => (
          <div key={i.prodID} style={{ padding: '10px', flex: '0 0 200px', height: '200px' }}>

<ProductCard
  id={i.prodID}
  prodDisc={i.disc}
  prodPoints={i.pointsRedeem}
  prodName={i.prodName}
  imgpath={i.imgPath}
  prodShortDesc={i.prodShortDesc}
  offerPrice={i.offerPrice}
  mrpPrice={i.mrpPrice}
  prodLongDesc={i.prodLongDesc}
/> </div>
           ))}
    </div>
  )
}

export default Product;
import React, { useState, useEffect } from 'react';
import './OrderPage.css';
import { useNavigate, useParams } from 'react-router-dom';
import html2canvas from 'html2canvas';
import jsPDF from "jspdf";
import emailjs from'emailjs-com';



const OrderPage = () => {
  const [invoiceData, setInvoiceData] = useState(null);
  const [customerData, setCustomerData] = useState(null);
  const [productDetails, setProductDetails] = useState([]);


  const [loader,setLoader] = useState(false);

  const { invoiceId  } = useParams();

  const navigate = useNavigate();
  const [newpoints, setNewPoints] = useState(0);
  
  const updateCustomerPoints = async (newPoints1) => {
    try {
      const pointsToSend = Math.round(newPoints1);
      await fetch(`https://localhost:7040/Customer/points/${sessionStorage.getItem('custId')}/${pointsToSend}`, {
        method: 'PUT',
      });
      console.log('Customer points updated successfully');
    } catch (error) {
      console.error('Error updating customer points:', error);
    }
  };
  

  
  useEffect(() => {
    // Fetch invoice data from the API using invoiceId
    fetch(`https://localhost:7040/invoice/${invoiceId}`)
      .then((response) => response.json())
      .then((data) => {
        setInvoiceData(data);
  
        // Fetch customer details using custID from invoiceData
        fetch(`https://localhost:7040/Customer/${data.custid}`)
          .then((response) => response.json())
          .then((customer) => {
            setCustomerData(customer);
            
            // Calculate new points based on 10% of total amount plus previous points
            const newPoints = customer.points + data.totalAmt * 0.1;
            setNewPoints(newPoints);
            
            // Update customer points with the new points
             updateCustomerPoints(newPoints);
          })
          .catch((error) => {
            console.error('Error fetching customer data:', error);
          });
  
        // Fetch product details using invoiceId
        fetch(`https://localhost:7040/Invoicedetails/invoiceId/${invoiceId}`)
          .then((response) => response.json())
          .then((products) => {
            setProductDetails(products);
          })
          .catch((error) => {
            console.error('Error fetching product details:', error);
          });
      })
      .catch((error) => {
        console.error('Error fetching invoice data:', error);
      });
  }, [invoiceId]);
  
  

  if (!invoiceData || !customerData || productDetails.length === 0) {
    return <div>Loading...</div>;
  }

  const downloadPDF = async () => {
    // try {
    //   await updateCustomerPoints(newpoints);
    // } catch (error) {
    //   console.error('Error updating customer points:', error);
    //   return; // If updating points fails, stop the function
    // }
  
    const capture = document.querySelector('.invoice-page');
    setLoader(true);
    html2canvas(capture).then((canvas)=> {
      const imgData = canvas.toDataURL('img/png');
      const doc = new jsPDF('P','mm','a4');
      const componentWidth = doc.internal.pageSize.getWidth();
      const componentHeight = doc.internal.pageSize.getHeight();
      doc.addImage(imgData,'PNG',0,0,componentWidth,componentHeight);
      setLoader(false);
      doc.save('receipt.pdf');
    });
  };
  
  const goBack = () => {
    
    navigate('/cart');
  }
 

  return (
    <>
  <div className="invoice-page">
    <div className="invoice-header">
      <h1>E-MART</h1>
      <hr />
      <h1>INVOICE</h1>
      <div className="invoice-date">{invoiceData.invoiceDate}</div>
    </div>
    <div className="invoice-customer-details">
      <div className="customer-field">
        <span>Name:</span> {customerData.custName}
      </div>
      <div className="customer-field">
        <span>Address:</span> {customerData.custAddress}
      </div>
      <div className="customer-field">
        <span>Contact:</span> {customerData.custPhone}
      </div>
    </div>
    <div className="invoice-products">
      <table className="products-table">
        <thead>
          <tr>
            <th>Product Name</th>
            <th>Product ID</th>
            <th>MRP</th>
            <th>Points Redeem</th>
            <th>Card Holder Price</th>
          </tr>
        </thead>
        <tbody>
          {productDetails.map((product) => (
            <tr key={product.prodid}>
              <td>{product.prodName}</td>
              <td>{product.prodid}</td>
              <td>₹{product.mrp}</td>
              <td>{product.pointsRedeem}</td>
              <td>₹{product.cardHolderPrice}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    <div className="invoice-totals">
      <div className="invoice-field">
        <span>Total Amount:</span> ₹{invoiceData.totalAmt.toFixed(2)}
      </div>
      <div className="invoice-field">
        <span>Delivery Charge:</span> ₹{invoiceData.deliveryCharge.toFixed(2)}
      </div>
      <div className="invoice-field">
        <span>Tax:</span> ₹{invoiceData.tax.toFixed(2)}
      </div>
      <div className="invoice-field">
        <span>Total Bill:</span> ₹{invoiceData.totalBill.toFixed(2)}
      </div>
    </div>
  </div>
  <div className="buttons">
    <button
      className="confirm-button"
      onClick={downloadPDF}
      disabled={!(loader === false)}
    >
      {loader ? <span>Sending Mail</span> : <span>Confirm</span>}
    </button>
    <button className="back-button" onClick={goBack}>
      Back
    </button>
  </div>
</>


  );
};

export default OrderPage;
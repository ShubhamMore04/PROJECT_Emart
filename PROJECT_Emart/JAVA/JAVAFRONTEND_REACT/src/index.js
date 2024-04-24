import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import App from './App';
import RegistrationForm from './Component/Registrationform'; 
import Login from './Component/Login'; // Check the correct path
import reportWebVitals from './reportWebVitals';
import NotFoundPage from './Component/Error';
import AboutPage from './Component/AboutPage';
import HomePage from './Component/HomePage';
import Subcategories from './Component/Subcategories';
import Product from './Component/Product';
import CombinedCartPage from './Component/CombinedCartPage';
import CategoriesPage from './Component/CategoriesPage';
import OrderPage from './Component/Orderpage';
ReactDOM.createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />}>
        <Route path="/Home" element={< HomePage/>} />
        <Route path="/register" element={<RegistrationForm />} />
        <Route path="/login" element={<Login />} />
        <Route path="/AboutPage" element={<AboutPage />} />
        <Route path="/subcat/:id" element={<Subcategories />} />
        <Route path="/product/:id" element={<Product/>} />
        <Route path="/cartpage" element={ <CombinedCartPage/> }/>
        <Route path="/categories" element={<CategoriesPage />} />
        <Route path="/order" element={<OrderPage />} />
        <Route path="*" element={<NotFoundPage />} />
      </Route>
    </Routes>
  </BrowserRouter>
);

reportWebVitals();

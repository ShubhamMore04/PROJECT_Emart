import React from 'react';
import CustomNavbar from './Component/Navbar'; // Add this line
import { Outlet } from 'react-router-dom'; // Add this line
import HomePage from './Component/HomePage';


function App() {
  return (
    <>
       <CustomNavbar />
      
      <Outlet />
    </>
  );
}

export default App;

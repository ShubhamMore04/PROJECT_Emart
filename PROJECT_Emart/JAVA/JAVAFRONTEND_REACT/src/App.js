import React from 'react';
import CustomNavbar from './Component/Navbar'; // Add this line
import { Outlet } from 'react-router-dom'; // Add this line


function App() {
  return (
    <>
       <CustomNavbar />
      <Outlet />
    </>
  );
}

export default App;

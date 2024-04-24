import React, { useState } from "react";
import "./Registrationform.css";
import { useNavigate } from "react-router-dom";

const Registrationform = () => {
  const [formState, setFormState] = useState({
    custName: "",
    custAddress: "",
    custPhone: "",
    custEmail: "",
    custPassword: "",
    confirm_password: "",
    cardHolder: false,
  });

  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  const navigate = useNavigate();

  const handleLogin = () => {
    navigate('/login');
  };

  const handleChange = (e) => {
    setFormState({
      ...formState,
      [e.target.name]: e.target.value,
    });
  };
  const handleClear = () => {
    setFormState({
      custName: "",
      custAddress: "",
      custPhone: "",
      custEmail: "",
      custPassword: "",
      confirm_password: "",
      cardHolder: false,
    });
  };

  const handleCheckboxChange = (e) => {
    setFormState({
      ...formState,
      [e.target.name]: e.target.checked,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setIsSubmitting(true);

    try {
      const response = await fetch("https://localhost:7040/Customer", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formState),
      });

      if (response.ok) {
        console.log("Registration Successful");
        alert("Registration Successful");
        navigate('/login');
      } else {
        console.error("Registration failed");
        alert("Registration failed");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Registration failed");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="bg-light min-vh-100 p-5 m-5">
      <h1>Please Register</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="custName">Name</label>
        <input
          id="custName"
          name="custName"
          type="text"
          placeholder="Enter your name"
          value={formState.custName}
          onChange={handleChange}
        />

        <label htmlFor="custAddress">Address</label>
        <input
          id="custAddress"
          name="custAddress"
          type="text"
          placeholder="Enter your address"
          value={formState.custAddress}
          onChange={handleChange}
        />

        <label htmlFor="custPhone">Phone</label>
        <input
          id="custPhone"
          name="custPhone"
          type="text"
          placeholder="Enter your phone number"
          value={formState.custPhone}
          onChange={handleChange}
        />

        <label htmlFor="custEmail">Email</label>
        <input
          id="custEmail"
          name="custEmail"
          type="email"
          placeholder="Enter your email"
          value={formState.custEmail}
          onChange={handleChange}
        />

        <label htmlFor="custPassword">Password</label>
        <input
          id="custPassword"
          name="custPassword"
          type="password"
          placeholder="Enter your password"
          value={formState.custPassword}
          onChange={handleChange}
        />

        <label htmlFor="confirm_password">Confirm Password</label>
        <input
          id="confirm_password"
          name="confirm_password"
          type="password"
          placeholder="Confirm your password"
          value={formState.confirm_password}
          onChange={handleChange}
        />
  <label htmlFor="cardHolder" style={{ marginBottom: '0' }}>Are you a CardHolder</label>
  <input
    id="cardHolder"
    name="cardHolder"
    type="checkbox"
    checked={formState.cardHolder}
    onChange={handleCheckboxChange}
    style={{ marginup: '2px' }} /* Add marginRight to give spacing between checkbox and label */
  />


<br></br>
<div className="d-flex justify-content-between">
  <button className="btn btn-primary" type="submit" disabled={isSubmitting}>
    Register
  </button>
  <div className="d-flex align-items-center">
    <p className="m-0">Already have an account?</p>
    <button className="btn btn-link" onClick={handleLogin}>
      Login
    </button>
  </div>
  <button className="btn btn-secondary" type="button" onClick={handleClear}>
    Clear
  </button>
</div>
      </form>
    </div>
  );
};

export default Registrationform;

import React, { useState } from 'react';
import { Form, Button, Card, Alert } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';
import "./Login.css"

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [loginError, setLoginError] = useState('');
  const [passwordShown, setPasswordShown] = useState(false);

  const togglePassword = () => {
    setPasswordShown(!passwordShown);
  };

  const handleLogin = async () => {
    try {
      const productResponse = await fetch('https://localhost:7040/Customer/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ custEmail: email, custPassword: password }),
      });

if (!productResponse.ok) {
  console.error('Error fetching product data:', productResponse.statusText);
  throw new Error('Failed to fetch product data');
}

const productData = await productResponse.json();
sessionStorage.setItem('custId',productData);
sessionStorage.setItem('islogin','true');

console.log('Product Data:', productData);
      alert('Emart Says : Login Successful');
      navigate('/Home');
    } catch (err) {
      console.log(err);
      setLoginError('Invalid credentials');
      
    }
  };

  
  

  const handleSubmit = (event) => {
    event.preventDefault();

    if (password.length < 8) {
      setPasswordError('Password must be at least 8 characters long');
      return;
    }

    setPasswordError('');
    setLoginError('');

    handleLogin();
  };

  return (
    <div className="login-background">
    <div className="d-flex justify-content-center align-items-center min-vh-100">
      <div className="col-md-6">
        <Card>
          <Card.Body>
            <div className="mb-3">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="formEmail" className="form-label">Email address</label>
                  <input
                    type="email"
                    className="form-control"
                    id="formEmail"
                    placeholder="Enter email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="formPassword" className="form-label">Password</label>
                  <div className="d-flex">
                    <input
                      type={passwordShown ? 'text' : 'password'}
                      className="form-control"
                      id="formPassword"
                      placeholder="Enter password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                    <button
                      style={{ fontSize: '20px' }}
                      id="togglepassview"
                      type="button"
                      className={passwordShown ? 'bi bi-eye-slash' : 'bi bi-eye'}
                      onClick={togglePassword}
                    ></button>
                  </div>
                  {passwordError && <Alert variant="danger">{passwordError}</Alert>}
                </div>
                <div className="mb-3 form-check">
                  <input type="checkbox" className="form-check-input" id="formRememberMe" />
                  <label className="form-check-label" htmlFor="formRememberMe">Remember me</label>
                </div>
                <div className="d-grid">
                  <Button variant="primary" type="submit">
                    Login
                  </Button>
                </div>
              </form>
              {loginError && <Alert variant="danger" className="mt-3">{loginError}</Alert>}
            </div>
          </Card.Body>
        </Card>
      </div>
    </div>
    </div>
  );
};

export default Login;

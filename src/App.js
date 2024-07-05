import React, { useState } from 'react';
import './App.css';
import DataFetcher from './DataFetcher';
import DataForm from './DataForm';
import LoginButton from './components/LoginButton';
import LogoutButton from './components/LogoutButton';
import Profile from './components/Profile';
import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import CheckoutForm from './components/CheckoutForm';

const stripePromise = loadStripe('your-publishable-key');

function App() {
  const [clientSecret, setClientSecret] = useState('');

  const createPaymentIntent = async () => {
    const response = await fetch('http://localhost:8080/api/stripe/create-payment-intent', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ amount: 1000, currency: 'usd', customerId: 'your-customer-id' }),
    });
    const data = await response.json();
    setClientSecret(data.clientSecret);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Welcome to the Dating App</h1>
        <LoginButton />
        <LogoutButton />
        <Profile />
        <DataFetcher />
        <DataForm />
        <button onClick={createPaymentIntent}>Add Money</button>
        {clientSecret && (
          <Elements stripe={stripePromise}>
            <CheckoutForm clientSecret={clientSecret} />
          </Elements>
        )}
      </header>
    </div>
  );
}

export default App;

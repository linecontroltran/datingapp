import React, { useState, useEffect } from 'react';
import { useAuth0 } from '@auth0/auth0-react';

const DataFetcher = () => {
  const { getAccessTokenSilently } = useAuth0();
  const [data, setData] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const token = await getAccessTokenSilently();
        const response = await fetch('http://localhost:8080/api/data', {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });

        if (!response.ok) {
          throw new Error('Network response was not ok');
        }

        const contentType = response.headers.get('content-type');
        if (contentType && contentType.indexOf('application/json') !== -1) {
          const data = await response.json();
          setData(data.message); // Adjust based on your JSON structure
        } else {
          throw new Error('Response is not JSON');
        }
        
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchData();
  }, [getAccessTokenSilently]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return <div>Data: {data}</div>;
};

export default DataFetcher;

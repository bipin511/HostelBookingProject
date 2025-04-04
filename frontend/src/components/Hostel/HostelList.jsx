import React, { useEffect, useState } from 'react';
import api from "../../axiosConfig"; 
import { Link } from 'react-router-dom';

const HostelList = () => {
  const [hostels, setHostels] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchHostels = async () => {
      try {
        const response = await api.get('http://localhost:8080/api/hostels');
        setHostels(response.data);
      } catch (err) {
        console.error(err);
        setError('Failed to load hostels.');
      }
    };
    fetchHostels();
  }, []);

  return (
    <div>
      <h2>Hostel List</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {hostels.length > 0 ? (
        hostels.map(hostel => (
          <div key={hostel.id}>
            <h3>{hostel.name}</h3>
            <p>{hostel.location}</p>
            <Link to={`/hostels/${hostel.id}`}>View Details</Link>
          </div>
        ))
      ) : (
        <p>No hostels found.</p>
      )}
    </div>
  );
};

export default HostelList;

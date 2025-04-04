import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminDashboard = () => {
  const [hostels, setHostels] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchHostels = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/hostels');
        // Filter hostels that are not verified
        setHostels(response.data.filter(hostel => !hostel.verified));
      } catch (err) {
        console.error(err);
        setError('Failed to load hostels for verification.');
      }
    };
    fetchHostels();
  }, []);

  const verifyHostel = async (id) => {
    try {
      await axios.put(`http://localhost:8080/api/admin/hostels/${id}/verify`);
      setHostels(hostels.filter(hostel => hostel.id !== id));
    } catch (err) {
      console.error(err);
      setError('Failed to verify hostel.');
    }
  };

  return (
    <div>
      <h2>Admin Dashboard</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <h3>Hostels Pending Verification</h3>
      {hostels.length > 0 ? (
        hostels.map(hostel => (
          <div key={hostel.id}>
            <p>{hostel.name} - {hostel.location}</p>
            <button onClick={() => verifyHostel(hostel.id)}>Verify</button>
          </div>
        ))
      ) : (
        <p>No hostels pending verification.</p>
      )}
    </div>
  );
};

export default AdminDashboard;

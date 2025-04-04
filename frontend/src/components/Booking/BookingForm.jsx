import React, { useState } from 'react';
import api from "../../axiosConfig"; 
import { useParams, useNavigate } from 'react-router-dom';

const BookingForm = () => {
  const { id } = useParams(); // 'id' here can represent the room id
  const navigate = useNavigate();
  const [checkIn, setCheckIn] = useState('');
  const [checkOut, setCheckOut] = useState('');
  const [error, setError] = useState('');

  const handleBooking = async (e) => {
    e.preventDefault();
    try {
      // Get a simulated userId from localStorage (or use a proper authentication mechanism)
      const userId = localStorage.getItem('userId') || 1;
      const bookingData = { userId, roomId: id, checkIn, checkOut };
      await api.post('http://localhost:8080/api/bookings', bookingData);
      navigate('/my-bookings');
    } catch (err) {
      console.error(err);
      setError('Booking failed.');
    }
  };

  return (
    <div>
      <h2>Book Room</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form onSubmit={handleBooking}>
        <div>
          <label>Check-In Date:</label>
          <input 
            type="date" 
            value={checkIn} 
            onChange={(e) => setCheckIn(e.target.value)} 
            required 
          />
        </div>
        <div>
          <label>Check-Out Date:</label>
          <input 
            type="date" 
            value={checkOut} 
            onChange={(e) => setCheckOut(e.target.value)} 
            required 
          />
        </div>
        <button type="submit">Book Now</button>
      </form>
    </div>
  );
};

export default BookingForm;

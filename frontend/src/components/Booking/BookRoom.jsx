import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../axiosConfig"; 

const BookRoom = () => {
  const { roomId } = useParams();
  const navigate = useNavigate();
  const [checkIn, setCheckIn] = useState("");
  const [checkOut, setCheckOut] = useState("");
  const user = JSON.parse(localStorage.getItem("user"));

  const handleBooking = async (e) => {
    e.preventDefault();

    try {
      await api.post("http://localhost:8080/api/bookings", {
        userId: user.userId,
        roomId,
        checkIn,
        checkOut
      });

      alert("Room booked successfully!");
      navigate("/hostels");
    } catch (error) {
      alert("Booking failed");
    }
  };

  return (
    <div style={{ maxWidth: "400px", margin: "40px auto" }}>
      <h2>Book Room</h2>
      <form onSubmit={handleBooking}>
        <label>Check-in Date:</label>
        <input
          type="date"
          value={checkIn}
          required
          onChange={(e) => setCheckIn(e.target.value)}
        />
        <br /><br />
        <label>Check-out Date:</label>
        <input
          type="date"
          value={checkOut}
          required
          onChange={(e) => setCheckOut(e.target.value)}
        />
        <br /><br />
        <button type="submit">Confirm Booking</button>
      </form>
    </div>
  );
};

export default BookRoom;

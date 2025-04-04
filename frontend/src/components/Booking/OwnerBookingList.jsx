// src/components/Booking/OwnerBookingList.jsx
import React, { useEffect, useState } from "react";
import api from "../../axiosConfig"; 

const OwnerBookingList = () => {
  const [bookings, setBookings] = useState([]);
  const [error, setError] = useState("");
  const user = JSON.parse(localStorage.getItem("user"));

  const fetchBookings = async () => {
    try {
      // Assuming the endpoint accepts ownerId (a Long) instead of email
      const res = await api.get(`http://localhost:8080/api/bookings/owner/${user.userId}`);
      setBookings(res.data);
    } catch (err) {
      console.error("Error loading bookings:", err);
      setError("Failed to load bookings");
    }
  };

  useEffect(() => {
    if (user && user.userId) {
      fetchBookings();
    } else {
      setError("User not found");
    }
  }, [user]);

  const handleApprove = async (bookingId) => {
    try {
      await api.put(`http://localhost:8080/api/bookings/${bookingId}/approve`);
      alert("Booking approved!");
      fetchBookings();
    } catch (err) {
      console.error("Approval failed", err);
      alert("Could not approve booking");
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-xl font-bold mb-4">Owner Booking Requests</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {bookings.length === 0 ? (
        <p>No bookings found</p>
      ) : (
        bookings.map((booking) => (
          <div key={booking.bookingId} className="border rounded p-4 mb-4 shadow">
            <p><strong>Room:</strong> {booking.room?.roomNumber}</p>
            <p><strong>Seeker:</strong> {booking.user?.email}</p>
            <p><strong>Check-In:</strong> {booking.checkIn}</p>
            <p><strong>Status:</strong> {booking.status}</p>
            {booking.status === "requested" && (
              <button
                onClick={() => handleApprove(booking.bookingId)}
                className="bg-green-600 text-white px-4 py-2 mt-2 rounded"
              >
                Approve
              </button>
            )}
          </div>
        ))
      )}
    </div>
  );
};

export default OwnerBookingList;

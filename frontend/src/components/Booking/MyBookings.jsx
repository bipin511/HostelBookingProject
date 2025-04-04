// src/components/Booking/MyBookings.jsx
import React, { useEffect, useState } from "react";
import api from "../../axiosConfig"; 

const MyBookings = () => {
  const [bookings, setBookings] = useState([]);
  const [error, setError] = useState("");
  const user = JSON.parse(localStorage.getItem("user"));

  const fetchBookings = async () => {
    try {
      const res = await api.get(`http://localhost:8080/api/bookings/user/${user.userId}`);
      setBookings(res.data);
    } catch (err) {
      console.error("Error loading bookings", err);
      setError("Failed to load bookings");
    }
  };

  useEffect(() => {
    if (!user || !user.userId) {
      setError("User not found");
      return;
    }
    fetchBookings();
  }, [user]);

  const handleCancel = async (bookingId) => {
    try {
      await api.put(`http://localhost:8080/api/bookings/${bookingId}/cancel`);
      alert("Booking cancelled successfully");
      fetchBookings();
    } catch (err) {
      console.error("Cancel error:", err);
      alert("Failed to cancel booking");
    }
  };

  return (
    <div style={{ padding: "20px", maxHeight: "calc(100vh - 80px)", overflowY: "auto" }}>
      <h2>My Bookings</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {bookings.length === 0 ? (
        <p>No bookings found.</p>
      ) : (
        <div style={{ display: "grid", gap: "20px" }}>
          {bookings.map((booking) => (
            <div key={booking.bookingId} style={{ border: "1px solid #ccc", borderRadius: "8px", padding: "10px" }}>
              <p><strong>Room Number:</strong> {booking.room?.roomNumber || "N/A"}</p>
              <p><strong>Type:</strong> {booking.room?.type || "N/A"}</p>
              <p><strong>Rent:</strong> ₹{booking.room?.rent || "N/A"}</p>
              <p><strong>Check-In:</strong> {booking.checkIn}</p>
              <p><strong>Check-Out:</strong> {booking.checkOut}</p>
              <p><strong>Status:</strong> {booking.status}</p>
              {(booking.status === "requested" || booking.status === "approved") && (
                <button
                  onClick={() => handleCancel(booking.bookingId)}
                  style={{
                    marginTop: "10px",
                    backgroundColor: "#dc2626",
                    color: "#fff",
                    padding: "8px 16px",
                    border: "none",
                    borderRadius: "4px",
                    cursor: "pointer"
                  }}
                >
                  Cancel Booking
                </button>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default MyBookings;

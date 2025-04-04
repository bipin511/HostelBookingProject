// components/Owner/ManageRooms.jsx
import React, { useEffect, useState } from "react";
import api from "../../axiosConfig"; 

import { Link, useParams } from "react-router-dom";

const ManageRooms = () => {
  const { hostelId } = useParams();
  const [rooms, setRooms] = useState([]);
  const [error, setError] = useState("");

  // Fetch all rooms for the given hostel
  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const res = await api.get(`http://localhost:8080/api/rooms/hostel/${hostelId}`);
        setRooms(res.data);
      } catch (err) {
        console.error("Error fetching rooms:", err);
        setError("Failed to load rooms");
      }
    };

    fetchRooms();
  }, [hostelId]);

  // Handle deletion of a room
  const handleDelete = async (roomId) => {
    try {
      await api.delete(`http://localhost:8080/api/rooms/${roomId}`);
      alert("Room deleted successfully");
      // Remove deleted room from the state
      setRooms((prev) => prev.filter((r) => r.id !== roomId));
    } catch (err) {
      console.error("Delete error:", err);
      // You can choose to hide the delete button permanently (if deletion is not allowed)
      // or simply alert the user that deletion failed.
      alert("Failed to delete room. This room has active bookings.");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Manage Rooms for Hostel #{hostelId}</h2>
      <Link to={`/owner/add-room/${hostelId}`} style={{ color: "blue" }}>
        + Add New Room
      </Link>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <ul style={{ marginTop: "20px" }}>
        {rooms.map((room) => (
          <li key={room.id} style={{ marginBottom: "10px" }}>
            <strong>Room {room.roomNumber}</strong> - {room.type} - Rent: {room.rent}
            &nbsp;|&nbsp;
            <Link to={`/owner/edit-room/${room.id}`} style={{ marginRight: "10px" }}>
              Edit
            </Link>
            {/* If you want to always show the delete button (and catch errors on click) */}
            <button
              style={{ backgroundColor: "red", color: "#fff", border: "none", padding: "5px 10px", borderRadius: "4px" }}
              onClick={() => handleDelete(room.id)}
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ManageRooms;

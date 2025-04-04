import React, { useEffect, useState } from "react";
import api from "../../axiosConfig"; 

const RoomList = ({ hostelId }) => {
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    api.get(`http://localhost:8080/api/rooms/hostel/${hostelId}`)
      .then(res => setRooms(res.data))
      .catch(err => console.error("Failed to fetch rooms:", err));
  }, [hostelId]);

  return (
    <div>
      <h3>Rooms in Hostel #{hostelId}</h3>
      <div style={{ display: "flex", flexWrap: "wrap", gap: "20px" }}>
        {rooms.map((room) => (
          <div
            key={room.id}
            style={{
              border: "1px solid #ccc",
              borderRadius: "8px",
              padding: "10px",
              width: "250px",
            }}
          >
            <img
              src={`http://localhost:8080/${room.images}`}
              alt="Room"
              style={{ width: "100%", height: "150px", objectFit: "cover" }}
            />
            <p><strong>Room No:</strong> {room.roomNumber}</p>
            <p><strong>Type:</strong> {room.type}</p>
            <p><strong>Rent:</strong> ₹{room.rent}</p>
            <p><strong>Available:</strong> {room.available ? "Yes" : "No"}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RoomList;

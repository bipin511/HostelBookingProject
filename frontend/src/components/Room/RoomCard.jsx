import React from "react";

const RoomCard = ({ room }) => {
  return (
    <div style={{ border: "1px solid #ddd", padding: "16px", margin: "10px", borderRadius: "10px" }}>
      <h4>Room: {room.roomNumber}</h4>
      <p>Type: {room.type}</p>
      <p>Rent: ₹{room.rent}</p>

      {/* ✅ IMAGE */}
      {room.images && (
        <img
          src={`http://localhost:8080/uploads/${room.images}`}
          alt="Room"
          style={{ width: "100%", maxHeight: "200px", objectFit: "cover", borderRadius: "10px" }}
        />
      )}
    </div>
  );
};

export default RoomCard;

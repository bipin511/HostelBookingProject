import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../axiosConfig"; 

const EditRoom = () => {
  const { roomId } = useParams();
  const navigate = useNavigate();

  // Local state to hold room data
  const [formData, setFormData] = useState({
    roomNumber: "",
    type: "",
    rent: "",
  });

  // Fetch existing room info on mount
  useEffect(() => {
    const fetchRoom = async () => {
      try {
        const res = await api.get(`http://localhost:8080/api/rooms/${roomId}`);
        setFormData({
          roomNumber: res.data.roomNumber || "",
          type: res.data.type || "",
          rent: res.data.rent || "",
        });
      } catch (err) {
        console.error("Error fetching room:", err);
        alert("Failed to load room details");
      }
    };

    fetchRoom();
  }, [roomId]);

  // Handle changes in form inputs
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Submit updated room data
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.put(`http://localhost:8080/api/rooms/${roomId}`, {
        roomNumber: formData.roomNumber,
        type: formData.type,
        rent: parseFloat(formData.rent),
      });
      alert("Room updated successfully!");
      // Option 1: Navigate back to Manage Rooms
      navigate(-1); 
      // or
      // Option 2: If you prefer to go specifically to ManageRooms:
      // navigate(`/owner/manage-rooms/${hostelId}`);
    } catch (err) {
      console.error("Error updating room:", err);
      alert("Failed to update room");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Edit Room #{roomId}</h2>
      <form onSubmit={handleSubmit} style={{ display: "grid", gap: "12px", maxWidth: "300px" }}>
        <label>
          Room Number:
          <input
            name="roomNumber"
            value={formData.roomNumber}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Type (AC/Non-AC):
          <input
            name="type"
            value={formData.type}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Rent:
          <input
            type="number"
            name="rent"
            value={formData.rent}
            onChange={handleChange}
            required
          />
        </label>
        <button type="submit" style={{ marginTop: "10px" }}>
          Save Changes
        </button>
      </form>
    </div>
  );
};

export default EditRoom;

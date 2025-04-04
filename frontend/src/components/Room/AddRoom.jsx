import React, { useState } from "react";
import api from "../../axiosConfig"; 
import { useParams, useNavigate } from "react-router-dom";

const AddRoom = () => {
  const { hostelId } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    roomNumber: "",
    type: "AC",
    rent: "",
    image: null,
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "image") {
      setFormData({ ...formData, image: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      let uploadedFileName = "";
      if (formData.image) {
        // Upload the image first
        const imageForm = new FormData();
        imageForm.append("file", formData.image);

        const uploadRes = await api.post("http://localhost:8080/api/rooms/upload", imageForm, {
          headers: { "Content-Type": "multipart/form-data" },
        });
        uploadedFileName = uploadRes.data;
      }

      // Then add the room
      await api.post("http://localhost:8080/api/rooms", {
        roomNumber: formData.roomNumber,
        type: formData.type,
        rent: parseFloat(formData.rent),
        hostelId: parseInt(hostelId),
        available: true,
        occupied: false,
        images: uploadedFileName, // store the filename
      });

      alert("Room added successfully!");
      navigate("/owner/my-hostels"); // or wherever you want to go
    } catch (error) {
      console.error("Error adding room:", error);
      alert("Room addition failed");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Add Room to Hostel #{hostelId}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Room Number:</label>
          <input
            name="roomNumber"
            placeholder="Room #"
            value={formData.roomNumber}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Type (AC/Non-AC):</label>
          <select name="type" value={formData.type} onChange={handleChange}>
            <option value="AC">AC</option>
            <option value="Non-AC">Non-AC</option>
          </select>
        </div>
        <div>
          <label>Rent:</label>
          <input
            name="rent"
            placeholder="Rent"
            value={formData.rent}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Image Upload:</label>
          <input
            type="file"
            name="image"
            accept="image/*"
            onChange={handleChange}
          />
        </div>
        <button type="submit">Add Room</button>
      </form>
    </div>
  );
};

export default AddRoom;

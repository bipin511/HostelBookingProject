import React, { useState } from "react";
import api from "../../axiosConfig";

const AddHostel = () => {
  const [name, setName] = useState("");
  const [location, setLocation] = useState("");
  const [gender, setGender] = useState("Male");
  const [rent, setRent] = useState("");
  const [image, setImage] = useState(null);

  const user = JSON.parse(localStorage.getItem("user"));

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      let imageName = "";

      if (image) {
        const imageData = new FormData();
        imageData.append("file", image);

        // ✅ POST with multipart/form-data
        const uploadRes = await api.post("/api/rooms/upload", imageData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });

        imageName = uploadRes.data;
      }

      const hostelData = {
        name,
        location,
        gender,
        rent: parseFloat(rent),
        ownerId: user.userId,
        images: imageName,
      };

      await api.post("/api/hostels", hostelData);
      alert("Hostel added successfully!");

      // Reset form
      setName("");
      setLocation("");
      setRent("");
      setImage(null);
    } catch (err) {
      console.error("Error adding hostel:", err);
      alert("Failed to add hostel");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Add Hostel</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Hostel Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Location"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
          required
        />
        <select value={gender} onChange={(e) => setGender(e.target.value)}>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Unisex">Unisex</option>
        </select>
        <input
          type="number"
          placeholder="Rent"
          value={rent}
          onChange={(e) => setRent(e.target.value)}
          required
        />
        <input
          type="file"
          accept="image/*"
          onChange={(e) => setImage(e.target.files[0])}
        />
        <button type="submit">Add Hostel</button>
      </form>
    </div>
  );
};

export default AddHostel;

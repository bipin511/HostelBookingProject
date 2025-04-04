import React, { useEffect, useState } from "react";
import api from "../../axiosConfig"; 

import { useParams, useNavigate } from "react-router-dom";

const EditHostel = () => {
  const { id } = useParams(); // e.g. /owner/edit-hostel/123
  const navigate = useNavigate();
  const [form, setForm] = useState({
    name: "",
    location: "",
    rent: "",
    gender: "",
  });

  useEffect(() => {
    // Fetch existing hostel details
    const fetchHostel = async () => {
      try {
        const res = await api.get(`http://localhost:8080/api/hostels/${id}`);
        setForm(res.data);
      } catch (err) {
        console.error(err);
        alert("Failed to load hostel details");
      }
    };
    fetchHostel();
  }, [id]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.put(`http://localhost:8080/api/hostels/owner/${id}`, form);
      alert("Hostel updated successfully");
      navigate("/owner/dashboard");
    } catch (err) {
      console.error(err);
      alert("Failed to update hostel");
    }
  };

  return (
    <div>
      <h2>Edit Hostel</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name: </label>
          <input
            name="name"
            value={form.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Location: </label>
          <input
            name="location"
            value={form.location}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Rent: </label>
          <input
            type="number"
            name="rent"
            value={form.rent}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Gender: </label>
          <select name="gender" value={form.gender} onChange={handleChange}>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="mixed">Mixed</option>
          </select>
        </div>
        <button type="submit">Update Hostel</button>
      </form>
    </div>
  );
};

export default EditHostel;

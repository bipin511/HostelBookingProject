import React, { useEffect, useState } from "react";
import api from "../../axiosConfig"; 
import { Link } from "react-router-dom";

const OwnerHostelList = () => {
  const [hostels, setHostels] = useState([]);
  const [error, setError] = useState("");

  const user = JSON.parse(localStorage.getItem("user"));

  const fetchHostels = async () => {
    if (!user || !user.userId) {
      setError("User ID not found. Please login again.");
      return;
    }

    try {
      const res = await api.get(`http://localhost:8080/api/hostels/owner/${user.userId}`);
      setHostels(res.data);
    } catch (err) {
      console.error("Failed to fetch hostels:", err);
      setError("Failed to load hostels");
    }
  };

  useEffect(() => {
    fetchHostels();
    // eslint-disable-next-line
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h2>My Hostels</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}

      {hostels.length === 0 ? (
        <p>No hostels found. Try adding one.</p>
      ) : (
        <ul>
          {hostels.map((hostel) => (
            <li key={hostel.id} style={{ marginBottom: "10px" }}>
            <strong>{hostel.name}</strong> - {hostel.location} {" | "}
            <Link to={`/owner/add-room/${hostel.id}`} style={{ marginLeft: "10px" }}>
              Add Room
            </Link>
            {" | "}
            <Link to={`/owner/manage-rooms/${hostel.id}`}>
              Manage Rooms
            </Link>
          </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default OwnerHostelList;

import React from "react";
import { Link } from "react-router-dom";
import "../../App.css"; // Ensure you import styles if not already

const OwnerDashboard = () => {
  return (
    <div className="app-container">
      <div className="login-card"> {/* reuse card styling */}
      <button
  onClick={() => {
    localStorage.clear();
    window.location.href = "/login";
  }}
  style={{ marginTop: "15px", backgroundColor: "#dc3545" }}
>
  Logout
</button>

        <h2>Owner Dashboard</h2>
        <ul style={{ listStyle: "none", paddingLeft: 0 }}>
          <li><Link to="/owner/add-hostel">➕ Add Hostel</Link></li>
          <li><Link to="/owner/my-hostels">🏠 My Hostels</Link></li>
        </ul>
      </div>
    </div>
  );
};

export default OwnerDashboard;

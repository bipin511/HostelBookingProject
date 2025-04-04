import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const role = user?.role?.toLowerCase();

  return (
    <nav style={{ padding: "10px", background: "#f3f3f3", display: "flex", gap: "20px" }}>
      <Link to="/">Home</Link>

      {!user && (
        <>
          <Link to="/login">Login</Link>
          <Link to="/register">Register</Link>
        </>
      )}

      {user && role === "seeker" && (
        <>
          <Link to="/my-bookings">My Bookings</Link>
        </>
      )}

      {user && role === "owner" && (
        <>
          <Link to="/owner/dashboard">Dashboard</Link>
          <Link to="/owner/my-hostels">My Hostels</Link>
        </>
      )}

      {user && (
        <button
          onClick={() => {
            localStorage.removeItem("user");
            window.location.href = "/";
          }}
        >
          Logout
        </button>
      )}
    </nav>
  );
};

export default Navbar;

// src/components/Auth/Login.jsx
import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        { username, password },
        { headers: { "Content-Type": "application/json" } }
      );

      alert("Login successful");

      const user = {
        userId: response.data.userId,
        role: response.data.role,
        email: response.data.email,
        token: response.data.token,
      };
      localStorage.setItem("user", JSON.stringify(user));

      const role = response.data.role.toLowerCase();
      if (role === "owner") {
        navigate("/owner/dashboard");
      } else if (role === "seeker") {
        navigate("/hostels");
      } else {
        navigate("/");
      }
    } catch (error) {
      alert("Login failed: Invalid credentials");
    }
  };

  return (
    <div className="center-page">
      <div className="card-container">
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            required
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            required
            onChange={(e) => setPassword(e.target.value)}
          />
          <button type="submit">Login</button>
        </form>
        <p>
          Don't have an account? <Link to="/register">Register here</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;

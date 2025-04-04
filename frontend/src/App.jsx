// src/App.jsx
import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";

// Navbar
import Navbar from "./components/Common/Navbar";

// Auth
import Login from "./components/Auth/Login";
import Register from "./components/Auth/Register";

// Owner
import AddHostel from "./components/Owner/AddHostel";
import EditHostel from "./components/Owner/EditHostel";
import ManageRooms from "./components/Owner/ManageRooms";
import OwnerDashboard from "./components/Owner/OwnerDashboard";
import OwnerHostelList from "./components/Owner/OwnerHostelList";
import AddRoom from "./components/Room/AddRoom";
import OwnerBookingList from "./components/Booking/OwnerBookingList";

// Seeker
import HostelList from "./components/Hostel/HostelList";
import RoomList from "./components/Room/RoomList";
import BookingForm from "./components/Booking/BookingForm";
import MyBookings from "./components/booking/MyBookings"; 

// Admin
import AdminDashboard from "./components/Admin/AdminDashboard";

// Shared / Common
import HostelDetails from "./components/Hostel/HostelDetails";
import BookRoom from "./components/booking/BookRoom";
import EditRoom from "./components/Room/EditRoom";





function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />

        {/* Authentication */}
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Owner */}
        <Route path="/owner/dashboard" element={<OwnerDashboard />} />
        <Route path="/owner/add-hostel" element={<AddHostel />} />
        <Route path="/owner/edit-hostel/:id" element={<EditHostel />} />
        <Route path="/owner/manage-rooms/:hostelId" element={<ManageRooms />} />
        <Route path="/owner/my-hostels" element={<OwnerHostelList />} />
        <Route path="/owner/add-room/:hostelId" element={<AddRoom />} />
        <Route path="/owner/bookings" element={<OwnerBookingList />} />

        {/* Seeker */}
        <Route path="/hostels" element={<HostelList />} />
        <Route path="/rooms" element={<RoomList />} />
        <Route path="/my-bookings" element={<MyBookings />} />
        <Route path="/book/:roomId" element={<BookingForm />} />

        {/* Admin */}
        <Route path="/admin" element={<AdminDashboard />} />

        {/* Shared */}
        <Route path="/hostels/:hostelId" element={<HostelDetails />} />
        <Route path="/book-room/:roomId" element={<BookRoom />} />
        <Route path="/owner/edit-room/:roomId" element={<EditRoom />} />

        

      </Routes>
    </Router>
  );
}

export default App;

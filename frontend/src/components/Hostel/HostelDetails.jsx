import React, { useEffect, useState } from "react";
import api from "../../axiosConfig";
import { useParams } from "react-router-dom";

const HostelDetails = () => {
  const { hostelId } = useParams();
  const [hostel, setHostel] = useState(null);
  const [rooms, setRooms] = useState([]);
  const [error, setError] = useState("");
  const user = JSON.parse(localStorage.getItem("user"));

  const fetchRooms = async () => {
    try {
      const res = await api.get(`http://localhost:8080/api/rooms/hostel/${hostelId}`);
      setRooms(res.data);
    } catch (err) {
      console.error("Room fetch error:", err);
      setError("Failed to load rooms");
    }
  };

  useEffect(() => {
    const fetchDetails = async () => {
      try {
        const res = await api.get(`http://localhost:8080/api/hostels/${hostelId}`);
        setHostel(res.data);
        await fetchRooms();
      } catch (err) {
        console.error("Hostel fetch error:", err);
        setError("Failed to load data");
      }
    };

    fetchDetails();
  }, [hostelId]);

  const handleBookRoom = async (roomId) => {
    try {
      const today = new Date();
      const checkIn = today.toISOString().split("T")[0];
      const checkOut = new Date(today.setDate(today.getDate() + 2)).toISOString().split("T")[0];

      await api.post("http://localhost:8080/api/bookings", {
        userId: user.userId,
        roomId,
        checkIn,
        checkOut,
      });

      alert("Room booked successfully!");
      await fetchRooms();
    } catch (err) {
      console.error("Booking error:", err);
      alert("Failed to book room");
    }
  };

  const handleCancelBooking = async (bookingId) => {
    try {
      await api.delete(`http://localhost:8080/api/bookings/${bookingId}`);
      alert("Booking cancelled successfully!");
      await fetchRooms();
    } catch (err) {
      console.error("Cancel booking error:", err);
      alert("Failed to cancel booking");
    }
  };

  return (
    <div className="p-6">
      {hostel && (
        <div className="mb-6">
          <h2 className="text-2xl font-bold mb-2">{hostel.name}</h2>
          <p><strong>Location:</strong> {hostel.location}</p>
          <p><strong>Gender:</strong> {hostel.gender}</p>
          <p><strong>Rent:</strong> ₹{hostel.rent}</p>
          {hostel.images && (
            <img
              src={`http://localhost:8080/uploads/${hostel.images}`}
              alt="Hostel"
              style={{
                width: "100%",
                maxHeight: "250px",
                objectFit: "cover",
                borderRadius: "10px",
                marginTop: "10px"
              }}
            />
          )}
        </div>
      )}

      <h3 className="text-xl font-semibold mb-4">Available Rooms</h3>
      {error && <p className="text-red-500">{error}</p>}

      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {rooms.map((room) => (
          <div key={room.id} className="border p-4 rounded shadow">
            <p><strong>Room:</strong> {room.roomNumber}</p>
            <p><strong>Type:</strong> {room.type}</p>
            <p><strong>Rent:</strong> ₹{room.rent}</p>
            <p><strong>Available:</strong> {room.available ? "Yes" : "No"}</p>
            {room.images && (
              <img
                src={`http://localhost:8080/uploads/${room.images}`}
                alt="Room"
                style={{
                  width: "100%",
                  maxHeight: "200px",
                  objectFit: "cover",
                  borderRadius: "8px"
                }}
              />
            )}

            {user?.role?.toLowerCase() === "seeker" && (
              <>
                {room.available ? (
                  <button
                    onClick={() => handleBookRoom(room.id)}
                    className="mt-3 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                  >
                    Book Room
                  </button>
                ) : room.bookingId ? (
                  <button
                    onClick={() => handleCancelBooking(room.bookingId)}
                    className="mt-3 bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700"
                  >
                    Cancel Booking
                  </button>
                ) : (
                  <p className="text-red-500 font-semibold mt-3">Already Booked</p>
                )}
              </>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default HostelDetails;

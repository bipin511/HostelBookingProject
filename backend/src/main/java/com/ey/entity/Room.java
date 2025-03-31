package com.ey.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    // Many rooms belong to one hostel
    @ManyToOne
    @JoinColumn(name = "hostel_id")
    private Hostel hostel;
    
    // Dedicated name field for the room
    private String name;

    private String roomType;   // e.g., "AC Single", "Non-AC Double"
    private double rent;
    private boolean availability;
    
    // You can store amenities as a comma-separated string
    private String amenities;

    public Room() {}

    public Room(Long roomId, Hostel hostel, String name, String roomType, double rent, boolean availability, String amenities) {
        this.roomId = roomId;
        this.hostel = hostel;
        this.name = name;
        this.roomType = roomType;
        this.rent = rent;
        this.availability = availability;
        this.amenities = amenities;
    }

    // Getters and Setters

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", hostel=" + hostel +
                ", name='" + name + '\'' +
                ", roomType='" + roomType + '\'' +
                ", rent=" + rent +
                ", availability=" + availability +
                ", amenities='" + amenities + '\'' +
                '}';
    }
}

package com.ey.request;

public class RoomRequest {
    private String roomNumber; // new
    private String type;
    private Double rent;
    private boolean available;
    private String images;
    private boolean occupied;
    private Long hostelId;

    // Getters & Setters
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getRent() { return rent; }
    public void setRent(Double rent) { this.rent = rent; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }

    public Long getHostelId() { return hostelId; }
    public void setHostelId(Long hostelId) { this.hostelId = hostelId; }
}

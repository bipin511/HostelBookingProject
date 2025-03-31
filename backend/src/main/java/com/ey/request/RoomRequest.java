package com.ey.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RoomRequest {

    @NotNull(message = "Hostel ID is required")
    private Long hostelId;

    @NotBlank(message = "Room name is required")
    @Size(max = 100, message = "Room name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Room type is required")
    // Optionally enforce specific values: single, double, AC, non-AC
    @Pattern(regexp = "^(single|double|AC|non-AC)$", 
             message = "Room type must be one of: single, double, AC, non-AC")
    private String roomType;

    @Min(value = 1, message = "Rent must be a positive number")
    private double rent;

    // For a primitive boolean, validation is not necessary.
    private boolean availability;

    // Amenities are optional; adding a maximum length constraint.
    @Size(max = 500, message = "Amenities must not exceed 500 characters")
    private String amenities;

    // Getters and Setters
    public Long getHostelId() {
        return hostelId;
    }

    public void setHostelId(Long hostelId) {
        this.hostelId = hostelId;
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
}

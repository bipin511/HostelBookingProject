package com.ey.response;

import com.ey.entity.Hostel;

public class HostelResponse {

    private Long hostelId;
    private String name;
    private String location;
    private String description; // New field for hostel description
    private boolean isVerified;

    public HostelResponse() {}

    public HostelResponse(Long hostelId, String name, String location, String description, boolean isVerified) {
        this.hostelId = hostelId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.isVerified = isVerified;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    // Mapper: Converts a Hostel entity to HostelResponse DTO
    public static HostelResponse convertToHostelResponse(Hostel hostel) {
        HostelResponse response = new HostelResponse();
        response.setHostelId(hostel.getHostelId());
        response.setName(hostel.getName());
        response.setLocation(hostel.getLocation());
        response.setDescription(hostel.getDescription()); // Maps description
        response.setVerified(hostel.isVerified());
        return response;
    }
}

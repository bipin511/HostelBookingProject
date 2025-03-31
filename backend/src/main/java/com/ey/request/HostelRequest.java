package com.ey.request;

import com.ey.entity.Hostel;
import com.ey.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HostelRequest {

    @NotNull(message = "Owner ID is required")
    private Long ownerId;

    @NotBlank(message = "Hostel name is required")
    @Size(max = 100, message = "Hostel name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;
    
    // Optional description field with maximum length of 500 characters
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    // isVerified is a boolean flag; no validation annotations are required.
    private boolean isVerified;

    public HostelRequest() {}

    public HostelRequest(Long ownerId, String name, String location, String description, boolean isVerified) {
        this.ownerId = ownerId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.isVerified = isVerified;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    // Mapper: Converts a HostelRequest into a Hostel entity.
    public static Hostel convertToHostel(HostelRequest request) {
        Hostel hostel = new Hostel();

        User owner = new User();
        owner.setUserId(request.getOwnerId()); // Set only the ID for mapping relation
        hostel.setOwner(owner);

        hostel.setName(request.getName());
        hostel.setLocation(request.getLocation());
        hostel.setDescription(request.getDescription());
        hostel.setVerified(request.isVerified());

        return hostel;
    }
}

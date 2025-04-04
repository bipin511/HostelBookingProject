package com.ey.request;



public class HostelRequest {
    private String name;
    private String location;
    private String description;
    private Double rent;
    private String amenities;
    private String gender;
    private String images;
    private Long ownerId; // ID of the User who owns this hostel

    public HostelRequest() {}

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getRent() { return rent; }
    public void setRent(Double rent) { this.rent = rent; }

    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}

package com.ey.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hostels")
public class Hostel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hostelId;

	// Many hostels belong to one owner (User)
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	private String name;
	private String location;

	@Column(length = 500)
	private String description;

	// Field to track whether this hostel is verified by an admin
	private boolean isVerified;

	// Representative rent for this hostel
	private double rent;

	// New fields for filtering
	private String amenities; // e.g. "WiFi,Parking,AC"
	private String gender; // e.g. "Male", "Female", "Coed"

	// No-args constructor
	public Hostel() {
	}

	// All-args constructor
	public Hostel(Long hostelId, User owner, String name, String location, String description, boolean isVerified,
			double rent, String amenities, String gender) {
		this.hostelId = hostelId;
		this.owner = owner;
		this.name = name;
		this.location = location;
		this.description = description;
		this.isVerified = isVerified;
		this.rent = rent;
		this.amenities = amenities;
		this.gender = gender;
	}

	// Getters and Setters
	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public void setVerified(boolean verified) {
		isVerified = verified;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
        this.gender = gender;
    }

@Override
    public String toString() {
        return "Hostel{" +
                "hostelId=" + hostelId +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", isVerified=" + isVerified +
                ", rent=" + rent +
                ", amenities='" + amenities + '\'' +
                ", gender='" + gender + '\'' +
                '}';
}
}

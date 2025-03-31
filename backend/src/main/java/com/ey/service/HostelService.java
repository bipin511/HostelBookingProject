package com.ey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.Hostel;
import com.ey.exception.ResourceNotFoundException;
import com.ey.repository.HostelRepository;
import com.ey.request.HostelRequest;
import com.ey.response.HostelResponse;

@Service
public class HostelService {

    @Autowired
    private HostelRepository hostelRepository;

    public HostelResponse createHostel(HostelRequest request) {
        Hostel hostel = new Hostel();
        hostel.setName(request.getName());
        hostel.setLocation(request.getLocation());
        // set other fields as needed

        Hostel savedHostel = hostelRepository.save(hostel);
        return convertToResponse(savedHostel);
    }

    public List<HostelResponse> getAllHostels() {
        List<Hostel> hostels = hostelRepository.findAll();
        List<HostelResponse> responses = new ArrayList<>();
        for (Hostel hostel : hostels) {
            responses.add(convertToResponse(hostel));
        }
        return responses;
    }

    public HostelResponse getHostelById(Long hostelId) {
        Hostel hostel = hostelRepository.findById(hostelId).orElse(null);
        if (hostel == null) {
            return null; // or throw a ResourceNotFoundException
        }
        return convertToResponse(hostel);
    }

    public String deleteHostel(Long hostelId) {
        if (!hostelRepository.existsById(hostelId)) {
            return "Hostel not found";
        }
        hostelRepository.deleteById(hostelId);
        return "Hostel deleted";
    }

    private HostelResponse convertToResponse(Hostel hostel) {
        HostelResponse response = new HostelResponse();
        response.setHostelId(hostel.getHostelId());
        response.setName(hostel.getName());
        response.setLocation(hostel.getLocation());
        return response;
    }
    
    public List<HostelResponse> searchHostels(Double minRent, Double maxRent, String amenities, String gender) {
        List<Hostel> hostels = hostelRepository.findAll();
        // For simplicity, filtering is done in-memory; for production, use JPA Specifications.
        return hostels.stream()
                .filter(h -> minRent == null || h.getRent() >= minRent)
                .filter(h -> maxRent == null || h.getRent() <= maxRent)
                .filter(h -> amenities == null || h.getAmenities().toLowerCase().contains(amenities.toLowerCase()))
                .filter(h -> gender == null || h.getGender().equalsIgnoreCase(gender))
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public HostelResponse updateHostel(Long hostelId, HostelRequest request) {
        Optional<Hostel> hostelOpt = hostelRepository.findById(hostelId);
        if (!hostelOpt.isPresent()) {
            throw new ResourceNotFoundException("Hostel not found for ID: " + hostelId);
        }
        Hostel hostel = hostelOpt.get();
        hostel.setName(request.getName());
        hostel.setLocation(request.getLocation());
        // Update additional fields like description, contact, etc.
        hostelRepository.save(hostel);
        return convertToResponse(hostel);
    }

    
}

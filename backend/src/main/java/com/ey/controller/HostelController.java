package com.ey.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ey.request.HostelRequest;
import com.ey.response.HostelResponse;
import com.ey.service.HostelService;

@RestController
@RequestMapping("/api/hostels")
@CrossOrigin(origins = "http://localhost:5173")
public class HostelController {

    @Autowired
    private HostelService hostelService;

    // Create a new hostel with validations applied on the request body
    @PostMapping
    public HostelResponse createHostel(@Valid @RequestBody HostelRequest request) {
        return hostelService.createHostel(request);
    }

    // Retrieve all hostels
    @GetMapping
    public List<HostelResponse> getAllHostels() {
        return hostelService.getAllHostels();
    }

    // Retrieve a hostel by ID
    @GetMapping("/{id}")
    public HostelResponse getHostelById(@PathVariable Long id) {
        return hostelService.getHostelById(id);
    }

    // Delete a hostel by ID
    @DeleteMapping("/{id}")
    public String deleteHostel(@PathVariable Long id) {
        return hostelService.deleteHostel(id);
    }
    
    // Search hostels based on parameters (for filtering/sorting)
    @GetMapping("/search")
    public List<HostelResponse> searchHostels(
        @RequestParam(required = false) Double minRent,
        @RequestParam(required = false) Double maxRent,
        @RequestParam(required = false) String amenities,
        @RequestParam(required = false) String gender) {
        return hostelService.searchHostels(minRent, maxRent, amenities, gender);
    }
    
    // Update hostel details with validations applied on the request body
    @PutMapping("/{hostelId}")
    public HostelResponse updateHostel(@PathVariable Long hostelId, @Valid @RequestBody HostelRequest request) {
        return hostelService.updateHostel(hostelId, request);
    }
}

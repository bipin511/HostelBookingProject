package com.ey.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.entity.Hostel;
import com.ey.entity.User;
import com.ey.repository.HostelRepository;
import com.ey.repository.UserRepository;
import com.ey.request.HostelRequest;

@RestController
@RequestMapping("/api/hostels")
@CrossOrigin(origins = "http://localhost:5173")
public class HostelController {

    @Autowired
    private HostelRepository hostelRepo;

    @Autowired
    private UserRepository userRepo;

    // 1. Add a new hostel
    @PostMapping
    
    public ResponseEntity<Hostel> addHostel(@RequestBody HostelRequest request) {
        // ✅ Use ownerId instead of ownerEmail
        User owner = userRepo.findById(request.getOwnerId())
            .orElseThrow(() -> new RuntimeException("Owner not found"));

        Hostel hostel = new Hostel();
        hostel.setName(request.getName());
        hostel.setLocation(request.getLocation());
        hostel.setDescription(request.getDescription());
        hostel.setGender(request.getGender());
        hostel.setRent(request.getRent());
        hostel.setAmenities(request.getAmenities());
        hostel.setImages(request.getImages());
        hostel.setOwner(owner);

        Hostel saved = hostelRepo.save(hostel);
        return ResponseEntity.ok(saved);
    }



    // 2. Edit a hostel
    @PutMapping("/owner/{id}")
    public ResponseEntity<Hostel> updateHostel(@PathVariable Long id, @RequestBody Hostel updated) {
        Hostel existing = hostelRepo.findById(id).orElseThrow(() -> new RuntimeException("Hostel not found"));
        existing.setName(updated.getName());
        existing.setLocation(updated.getLocation());
        existing.setRent(updated.getRent());
        existing.setGender(updated.getGender());
        // etc.
        Hostel saved = hostelRepo.save(existing);
        return ResponseEntity.ok(saved);
    }

    // 3. Delete a hostel
    @DeleteMapping("/owner/{id}")
    public ResponseEntity<String> deleteHostel(@PathVariable Long id) {
        if (!hostelRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hostel not found");
        }
        hostelRepo.deleteById(id);
        return ResponseEntity.ok("Hostel deleted");
    }

    // 4. Get all hostels by owner
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Hostel>> getHostelsByOwner(@PathVariable Long ownerId) {
        List<Hostel> hostels = hostelRepo.findByOwnerId(ownerId);
        return ResponseEntity.ok(hostels);
    }

    // 5. (Optional) Get single hostel by ID
    @GetMapping("/{id}")
    public ResponseEntity<Hostel> getHostelById(@PathVariable Long id) {
        Hostel hostel = hostelRepo.findById(id).orElse(null);
        if (hostel == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(hostel);
    }
    
    @GetMapping
    public ResponseEntity<List<Hostel>> getAllHostels() {
        List<Hostel> hostels = hostelRepo.findAll();
        return ResponseEntity.ok(hostels);
    }
}

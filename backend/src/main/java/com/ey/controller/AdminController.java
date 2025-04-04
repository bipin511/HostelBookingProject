package com.ey.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ey.entity.Hostel;
import com.ey.service.HostelService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private HostelService hostelService;

    // Admin verifies a hostel listing
    @PutMapping("/hostels/{id}/verify")
    public ResponseEntity<Hostel> verifyHostel(@PathVariable Long id) {
        Hostel hostel = hostelService.getHostelById(id);
        if (hostel == null) {
            return ResponseEntity.notFound().build();
        }
        hostel.setVerified(true);
        hostel = hostelService.updateHostel(hostel);
        return ResponseEntity.ok(hostel);
    }
}

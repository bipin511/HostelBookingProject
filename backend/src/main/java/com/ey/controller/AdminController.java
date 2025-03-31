package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.response.BookingResponse;
import com.ey.response.HostelResponse;
import com.ey.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/hostels/pending")
    public List<HostelResponse> getPendingHostels() {
        return adminService.getPendingHostels();
    }

    @PutMapping("/hostels/{hostelId}/verify")
    public String verifyHostel(@PathVariable Long hostelId) {
        return adminService.verifyHostel(hostelId);
    }

    @GetMapping("/bookings")
    public List<BookingResponse> getAllBookings() {
        return adminService.getAllBookings();
    }
}

package com.ey.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.Hostel;
import com.ey.repository.HostelRepository;

@Service
public class HostelService {

    @Autowired
    private HostelRepository hostelRepository;

    public Hostel addHostel(Hostel hostel) {
        // Owner adds hostel; initially not verified
        hostel.setVerified(false);
        return hostelRepository.save(hostel);
    }

    public List<Hostel> getAllVerifiedHostels() {
        return hostelRepository.findByVerifiedTrue();
    }

    public Hostel getHostelById(Long id) {
        return hostelRepository.findById(id).orElse(null);
    }

    public Hostel updateHostel(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    public void deleteHostel(Long id) {
        hostelRepository.deleteById(id);
    }
}

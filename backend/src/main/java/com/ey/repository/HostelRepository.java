package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ey.entity.Hostel;

public interface HostelRepository extends JpaRepository<Hostel, Long> {

    // Example custom query methods (optional)
    List<Hostel> findByLocation(String location);

    List<Hostel> findByIsVerified(boolean isVerified);

    List<Hostel> findByNameContainingIgnoreCase(String name);
}

package com.ey.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ey.entity.Hostel;

public interface HostelRepository extends JpaRepository<Hostel, Long> {

    List<Hostel> findByOwnerId(Long ownerId);

    // ✅ Add this method for fetching all verified hostels
    List<Hostel> findByVerifiedTrue();
}

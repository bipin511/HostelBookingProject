package com.ey.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByRoom_Hostel_Owner_Id(Long ownerId);
    List<Booking> findByRoomId(Long roomId);
    
    
}

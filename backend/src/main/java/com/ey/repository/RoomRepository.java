package com.ey.repository;

import com.ey.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHostelHostelId(Long hostelId);
}

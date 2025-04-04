package com.ey.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ey.entity.Booking;
import com.ey.entity.Hostel;
import com.ey.entity.Room;
import com.ey.repository.BookingRepository;
import com.ey.repository.HostelRepository;
import com.ey.repository.RoomRepository;
import com.ey.request.RoomRequest;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {
	
	@Autowired
	private BookingRepository bookingRepo;
	
	

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private HostelRepository hostelRepo;

    // 1) Get single room
    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable Long roomId) {
        Room room = roomRepo.findById(roomId).orElse(null);
        if (room == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(room);
    }

    // 2) Get all rooms for a hostel
    @GetMapping("/hostel/{hostelId}")
    public ResponseEntity<List<Room>> getRoomsByHostel(@PathVariable Long hostelId) {
        List<Room> rooms = roomRepo.findByHostelId(hostelId);
        return ResponseEntity.ok(rooms);
    }

    // 3) Add room
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody RoomRequest request) {
        Hostel hostel = hostelRepo.findById(request.getHostelId())
            .orElseThrow(() -> new RuntimeException("Hostel not found"));

        Room room = new Room();
        room.setHostel(hostel);
        room.setRoomNumber(request.getRoomNumber());
        room.setType(request.getType());
        room.setRent(request.getRent());
        room.setAvailable(request.isAvailable());
        room.setImages(request.getImages());
        room.setOccupied(request.isOccupied());

        Room saved = roomRepo.save(room);
        return ResponseEntity.ok(saved);
    }

    // 4) Delete a room (catching constraint violations)
    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long roomId) {
        List<Booking> bookings = bookingRepo.findByRoomId(roomId);
        if (!bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete room. Bookings exist for this room.");
        }

        if (!roomRepo.existsById(roomId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }

        roomRepo.deleteById(roomId);
        return ResponseEntity.ok("Room deleted successfully");
    }


    // 5) Update room
    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomId, @RequestBody Room updated) {
        Room existing = roomRepo.findById(roomId)
            .orElseThrow(() -> new RuntimeException("Room not found"));

        existing.setRoomNumber(updated.getRoomNumber());
        existing.setType(updated.getType());
        existing.setRent(updated.getRent());
        // Optionally update available, images, and occupied if desired:
        existing.setAvailable(updated.isAvailable());
        existing.setImages(updated.getImages());
        existing.setOccupied(updated.isOccupied());

        Room saved = roomRepo.save(existing);
        return ResponseEntity.ok(saved);
    }

    // 6) Image upload handler
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            File destination = new File(uploadDir + fileName);
            file.transferTo(destination);
            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error uploading image");
        }
    }
}

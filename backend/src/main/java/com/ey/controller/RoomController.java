package com.ey.controller;

import java.io.File;
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
import jakarta.validation.Valid;
import com.ey.request.RoomRequest;
import com.ey.response.RoomResponse;
import com.ey.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Add a new room with validations applied
    @PostMapping
    public String addRoom(@Valid @RequestBody RoomRequest request) {
        return roomService.addRoom(request);
    }

    // Retrieve rooms by hostel ID
    @GetMapping("/hostel/{hostelId}")
    public List<RoomResponse> getRoomsByHostel(@PathVariable Long hostelId) {
        return roomService.getRoomsByHostel(hostelId);
    }

    // Delete room by ID
    @DeleteMapping("/{roomId}")
    public String deleteRoom(@PathVariable Long roomId) {
        return roomService.deleteRoom(roomId);
    }

    // Upload an image for a specific room
    @PostMapping("/{roomId}/uploadImage")
    public ResponseEntity<String> uploadRoomImage(@PathVariable Long roomId,
                                                  @RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }
        try {
            // Define the directory for room images
            String uploadDir = "uploads/rooms/";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            // Create a unique filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;
            // Save the file to disk
            file.transferTo(new File(filePath));
            // Optionally update the Room entity with the image path:
            // roomService.updateRoomImage(roomId, fileName);
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error uploading file: " + e.getMessage());
        }
    }

    // Update room details with validations applied
    @PutMapping("/{roomId}")
    public ResponseEntity<String> updateRoom(@PathVariable Long roomId, @Valid @RequestBody RoomRequest request) {
        String message = roomService.updateRoom(roomId, request);
        return ResponseEntity.ok(message);
    }
}

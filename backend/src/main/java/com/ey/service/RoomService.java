package com.ey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.Hostel;
import com.ey.entity.Room;
import com.ey.repository.HostelRepository;
import com.ey.repository.RoomRepository;
import com.ey.request.RoomRequest;
import com.ey.response.RoomResponse;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HostelRepository hostelRepository;

    public String addRoom(RoomRequest request) {
        Hostel hostel = hostelRepository.findById(request.getHostelId()).orElse(null);
        if (hostel == null) {
            return "Hostel not found";
        }

        Room room = new Room();
        room.setHostel(hostel);
        room.setName(request.getName());
        room.setRoomType(request.getRoomType());
        room.setRent(request.getRent());
        room.setAvailability(request.isAvailability());
        room.setAmenities(request.getAmenities());

        roomRepository.save(room);
        return "Room added successfully";
    }

    public List<RoomResponse> getRoomsByHostel(Long hostelId) {
        List<Room> rooms = roomRepository.findByHostelHostelId(hostelId);
        List<RoomResponse> responses = new ArrayList<>();

        for (Room room : rooms) {
            RoomResponse res = new RoomResponse();
            res.setRoomId(room.getRoomId());
            res.setHostelName(room.getHostel().getName());
            res.setName(room.getName());
            res.setRoomType(room.getRoomType());
            res.setRent(room.getRent());
            res.setAvailability(room.isAvailability());
            res.setAmenities(room.getAmenities());
            responses.add(res);
        }

        return responses;
    }

    public String deleteRoom(Long roomId) {
        if (!roomRepository.existsById(roomId)) {
            return "Room not found";
        }
        roomRepository.deleteById(roomId);
        return "Room deleted";
    }
    
    public String updateRoom(Long roomId, RoomRequest request) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (!roomOpt.isPresent()) {
             return "Room not found";
        }
        Room room = roomOpt.get();
        room.setName(request.getName());
        room.setRoomType(request.getRoomType());
        room.setRent(request.getRent());
        room.setAvailability(request.isAvailability());
        room.setAmenities(request.getAmenities());
        roomRepository.save(room);
        return "Room updated successfully";
    }

}

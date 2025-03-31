package com.ey.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.User;
import com.ey.repository.UserRepository;
import com.ey.request.UserRequest;
import com.ey.response.UserResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Get all users and return as List of UserResponse
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(UserResponse::convertToUserResponse)
                    .collect(Collectors.toList());
    }

    // ✅ Create user from UserRequest and return UserResponse
    public UserResponse createUser(UserRequest userRequest) {
        User user = UserRequest.convertToUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserResponse.convertToUserResponse(savedUser);
    }

    // ✅ Get a single user by ID and return as UserResponse
    public UserResponse getUserResponseById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(UserResponse::convertToUserResponse).orElse(null);
    }

    // ✅ Delete user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

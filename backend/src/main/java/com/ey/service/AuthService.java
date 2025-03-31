package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.User;
import com.ey.repository.UserRepository;
import com.ey.request.AuthRequest;
import com.ey.request.UserRequest;
import com.ey.response.AuthResponse;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Login method: verifies email and password
    public AuthResponse login(AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());
        AuthResponse response = new AuthResponse();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(authRequest.getPassword())) {
                response.setMessage("Login successful");
                // Optionally generate and set a token, e.g.:
                // response.setToken(generateToken(user));
            } else {
                response.setMessage("Invalid password");
            }
        } else {
            response.setMessage("User not found");
        }

        return response;
    }

    // Registration method: creates a new user if one doesn't already exist
    public AuthResponse register(UserRequest userRequest) {
        AuthResponse response = new AuthResponse();
        Optional<User> existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            response.setMessage("User already exists");
            return response;
        }
        User user = UserRequest.convertToUser(userRequest);
        userRepository.save(user);
        response.setMessage("Registration successful");
        return response;
    }
}

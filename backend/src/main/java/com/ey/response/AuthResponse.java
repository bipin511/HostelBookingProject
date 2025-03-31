package com.ey.response;

public class AuthResponse {
    private String message;
    private String token; // Optional: include if using JWT or similar

    // Getters and setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}

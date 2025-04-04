package com.ey.response;

public class AuthResponse {
    private String message;
    private String email; // ✅ backend sends "email" field
    private String role;
    private Long userId;
    private String token;

    public AuthResponse(String message, String email, String role, Long userId,String token) {
        this.message = message;
        this.email = email;
        this.role = role;
        this.userId = userId;
        this.token = token;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getToken() {
	    return token;
	}
	public void setToken(String token) {
	    this.token = token;
	}

    // Getters and setters...
    
}

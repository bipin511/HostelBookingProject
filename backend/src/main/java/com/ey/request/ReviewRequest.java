package com.ey.request;

public class ReviewRequest {
    private Long userId;
    private Long hostelId;
    private int rating;
    private String comment;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getHostelId() {
		return hostelId;
	}
	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

    // Getters and Setters
    
    
}

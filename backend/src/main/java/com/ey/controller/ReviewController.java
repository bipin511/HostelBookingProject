package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.request.ReviewRequest;
import com.ey.response.ReviewResponse;
import com.ey.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ReviewResponse submitReview(@RequestBody ReviewRequest request) {
        return reviewService.submitReview(request);
    }

    @GetMapping("/hostel/{hostelId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByHostel(@PathVariable Long hostelId) {
        List<ReviewResponse> reviews = reviewService.getReviewsByHostelId(hostelId);
        return ResponseEntity.ok(reviews);
    }
}

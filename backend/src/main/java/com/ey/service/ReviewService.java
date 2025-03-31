package com.ey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.Review;
import com.ey.repository.HostelRepository;
import com.ey.repository.ReviewRepository;
import com.ey.repository.UserRepository;
import com.ey.request.ReviewRequest;
import com.ey.response.ReviewResponse;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HostelRepository hostelRepository;

    public ReviewResponse submitReview(ReviewRequest request) {
        Review review = new Review();
        review.setUser(userRepository.findById(request.getUserId()).orElse(null));
        review.setHostel(hostelRepository.findById(request.getHostelId()).orElse(null));
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        Review saved = reviewRepository.save(review);
        return convertToResponse(saved);
    }

    public List<ReviewResponse> getReviewsByHostelId(Long hostelId) {
        return reviewRepository.findByHostelHostelId(hostelId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private ReviewResponse convertToResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setReviewId(review.getReviewId());
        response.setUserId(review.getUser().getUserId());
        response.setHostelId(review.getHostel().getHostelId());
        response.setRating(review.getRating());
        response.setComment(review.getComment());
        return response;
    }
}

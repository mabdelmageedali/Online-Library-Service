package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.review.ReviewRequestDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewResponseDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewUpdateDTO;

import java.util.List;

public interface ReviewService {

    // Add new review for a book
    ReviewResponseDTO addReview(Integer userId, ReviewRequestDTO dto);

    // Update user's review for a book
    ReviewResponseDTO updateReview(Integer userId, Integer bookId, ReviewUpdateDTO dto);

    // Delete user's review for a book
    void deleteReview(Integer userId, Integer bookId);

    // Get all reviews for a book
    List<ReviewResponseDTO> getBookReviews(Integer bookId);

    // Get all reviews written by a user
    List<ReviewResponseDTO> getUserReviews(Integer userId);

    // Get average rating of a book
    Double getAverageRating(Integer bookId);
}
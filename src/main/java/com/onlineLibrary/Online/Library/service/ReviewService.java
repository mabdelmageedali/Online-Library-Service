package com.onlineLibrary.Online.Library.service;

import com.onlineLibrary.Online.Library.entity.Review;

import java.util.List;

public interface ReviewService {

    // Add new review for a book
    Review addReview(Integer userId, Integer bookId, Review review);

    // Update user's review for a book
    Review updateReview(Integer userId, Integer bookId, Review review);

    // Delete user's review for a book
    void deleteReview(Integer userId, Integer bookId);

    // Get all reviews for a book
    List<Review> getBookReviews(Integer bookId);

    // Get all reviews written by a user
    List<Review> getUserReviews(Integer userId);

    // Get average rating of a book
    Double getAverageRating(Integer bookId);
}

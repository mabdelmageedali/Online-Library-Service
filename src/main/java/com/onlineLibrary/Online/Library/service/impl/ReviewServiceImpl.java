package com.onlineLibrary.Online.Library.service.impl;


import com.onlineLibrary.Online.Library.entity.Book;
import com.onlineLibrary.Online.Library.entity.Review;
import com.onlineLibrary.Online.Library.entity.User;
import com.onlineLibrary.Online.Library.repository.BookRepository;
import com.onlineLibrary.Online.Library.repository.ReviewRepository;
import com.onlineLibrary.Online.Library.repository.UserRepository;
import com.onlineLibrary.Online.Library.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    // Add new review
    @Override
    public Review addReview(Integer userId, Integer bookId, Review review) {

        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if book exists
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if user already reviewed this book
        if (reviewRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new RuntimeException("You already reviewed this book");
        }

        // Link review with user and book
        review.setUser(user);
        review.setBook(book);

        // Save review
        return reviewRepository.save(review);
    }

    // Update review
    @Override
    public Review updateReview(Integer userId, Integer bookId, Review review) {

        Review oldReview = reviewRepository
                .findByUserIdAndBookId(userId, bookId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // Update fields
        oldReview.setRating(review.getRating());
        oldReview.setComment(review.getComment());

        return reviewRepository.save(oldReview);
    }

    // Delete review
    @Override
    public void deleteReview(Integer userId, Integer bookId) {

        if (!reviewRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new RuntimeException("Review not found");
        }

        reviewRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    // Get all reviews for book
    @Override
    public List<Review> getBookReviews(Integer bookId) {

        return reviewRepository.findByBookId(bookId);
    }

    // Get all reviews by user
    @Override
    public List<Review> getUserReviews(Integer userId) {

        return reviewRepository.findByUserId(userId);
    }

    // Get average rating
    @Override
    public Double getAverageRating(Integer bookId) {

        Double avg = reviewRepository.findAverageRatingByBookId(bookId);

        // If no reviews
        if (avg == null) {
            return 0.0;
        }

        return avg;
    }
}


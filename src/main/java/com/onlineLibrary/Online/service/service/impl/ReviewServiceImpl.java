package com.onlineLibrary.Online.service.service.impl;


import com.onlineLibrary.Online.service.entity.Book;
import com.onlineLibrary.Online.service.entity.Review;
import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.repository.BookRepository;
import com.onlineLibrary.Online.service.repository.ReviewRepository;
import com.onlineLibrary.Online.service.repository.UserRepository;
import com.onlineLibrary.Online.service.service.ReviewService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    // Add new review
    @Override
    public Review addReview(Integer userId, Integer bookId, Review review) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        if (reviewRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new BadRequestException("You already reviewed this book");
        }

        review.setUser(user);
        review.setBook(book);

        return reviewRepository.save(review);
    }

    // Update review
    @Override
    public Review updateReview(Integer userId, Integer bookId, Review review) {

        Review oldReview = reviewRepository
                .findByUserIdAndBookId(userId, bookId)
                .orElseThrow(() -> new NotFoundException("Review not found"));

        oldReview.setRating(review.getRating());
        oldReview.setComment(review.getComment());

        return reviewRepository.save(oldReview);
    }

    // Delete review
    @Override
    public void deleteReview(Integer userId, Integer bookId) {

        if (!reviewRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new NotFoundException("Review not found");
        }

        reviewRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    // Get all reviews for book
    @Override
    @Transactional(readOnly = true)
    public List<Review> getBookReviews(Integer bookId) {

        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found");
        }

        return reviewRepository.findByBookId(bookId);
    }

    // Get all reviews by user
    @Override
    @Transactional(readOnly = true)
    public List<Review> getUserReviews(Integer userId) {

        return reviewRepository.findByUserId(userId);
    }

    // Get average rating
    @Override
    @Transactional(readOnly = true)
    public Double getAverageRating(Integer bookId) {

        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found");
        }

        Double avg = reviewRepository.findAverageRatingByBookId(bookId);

        return avg == null ? 0.0 : avg;
    }
}
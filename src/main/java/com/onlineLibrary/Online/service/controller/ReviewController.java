package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.entity.Review;
import com.onlineLibrary.Online.service.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Add new review
    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<Review> addReview(
            @PathVariable Integer userId,
            @PathVariable Integer bookId,
            @Valid @RequestBody Review review
    ) {

        Review savedReview =
                reviewService.addReview(userId, bookId, review);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    // Update review
    @PutMapping("/{userId}/{bookId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Integer userId,
            @PathVariable Integer bookId,
            @Valid @RequestBody Review review
    ) {

        Review updatedReview =
                reviewService.updateReview(userId, bookId, review);

        return ResponseEntity.ok(updatedReview);
    }

    // Delete review
    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Integer userId,
            @PathVariable Integer bookId
    ) {

        reviewService.deleteReview(userId, bookId);

        return ResponseEntity.noContent().build();
    }

    // Get reviews of a book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getBookReviews(
            @PathVariable Integer bookId
    ) {

        return ResponseEntity.ok(
                reviewService.getBookReviews(bookId)
        );
    }

    // Get reviews of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getUserReviews(
            @PathVariable Integer userId
    ) {

        return ResponseEntity.ok(
                reviewService.getUserReviews(userId)
        );
    }

    // Get average rating of a book
    @GetMapping("/average/{bookId}")
    public ResponseEntity<Double> getAverageRating(
            @PathVariable Integer bookId
    ) {

        Double avg = reviewService.getAverageRating(bookId);

        return ResponseEntity.ok(avg);
    }
}

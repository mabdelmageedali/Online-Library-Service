package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.dto.review.ReviewRequestDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewResponseDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewUpdateDTO;
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

    @PostMapping("/user/{userId}")
    public ResponseEntity<ReviewResponseDTO> addReview(
            @PathVariable Integer userId,
            @Valid @RequestBody ReviewRequestDTO dto) {
        ReviewResponseDTO response = reviewService.addReview(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Integer userId,
            @PathVariable Integer bookId,
            @Valid @RequestBody ReviewUpdateDTO dto) {
        ReviewResponseDTO response = reviewService.updateReview(userId, bookId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Integer userId,
            @PathVariable Integer bookId) {
        reviewService.deleteReview(userId, bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReviewResponseDTO>> getBookReviews(@PathVariable Integer bookId) {
        return ResponseEntity.ok(reviewService.getBookReviews(bookId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getUserReviews(@PathVariable Integer userId) {
        return ResponseEntity.ok(reviewService.getUserReviews(userId));
    }


    @GetMapping("/average/book/{bookId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Integer bookId) {
        Double avg = reviewService.getAverageRating(bookId);
        return ResponseEntity.ok(avg);
    }
}
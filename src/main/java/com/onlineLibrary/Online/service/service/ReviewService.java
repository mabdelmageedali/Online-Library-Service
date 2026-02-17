package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.review.ReviewRequestDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewResponseDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewUpdateDTO;

import java.util.List;

public interface ReviewService {

    ReviewResponseDTO addReview(Integer userId, ReviewRequestDTO dto);

    ReviewResponseDTO updateReview(Integer userId, Integer bookId, ReviewUpdateDTO dto);

    void deleteReview(Integer userId, Integer bookId);

    List<ReviewResponseDTO> getBookReviews(Integer bookId);

    List<ReviewResponseDTO> getUserReviews(Integer userId);

    Double getAverageRating(Integer bookId);
}
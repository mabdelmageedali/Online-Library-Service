package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.dto.review.ReviewRequestDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewResponseDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewUpdateDTO;
import com.onlineLibrary.Online.service.entity.Book;
import com.onlineLibrary.Online.service.entity.Review;
import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.mapper.ReviewMapper;
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
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewResponseDTO addReview(Integer userId, ReviewRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + dto.getBookId()));

        if (reviewRepository.existsByUserIdAndBookId(userId, dto.getBookId())) {
            throw new BadRequestException("You already reviewed this book");
        }

        Review review = reviewMapper.toEntity(dto);
        review.setUser(user);
        review.setBook(book);

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toResponseDTO(savedReview);
    }

    @Override
    public ReviewResponseDTO updateReview(Integer userId, Integer bookId, ReviewUpdateDTO dto) {
        Review review = reviewRepository.findByUserIdAndBookId(userId, bookId)
                .orElseThrow(() -> new NotFoundException("Review not found"));

        reviewMapper.updateEntityFromDTO(dto, review);
        Review updatedReview = reviewRepository.save(review);
        return reviewMapper.toResponseDTO(updatedReview);
    }

    @Override
    public void deleteReview(Integer userId, Integer bookId) {
        if (!reviewRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new NotFoundException("Review not found");
        }

        reviewRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> getBookReviews(Integer bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found with id: " + bookId);
        }

        List<Review> reviews = reviewRepository.findByBookId(bookId);
        return reviewMapper.toResponseDTOList(reviews);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> getUserReviews(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }

        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviewMapper.toResponseDTOList(reviews);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAverageRating(Integer bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book not found with id: " + bookId);
        }

        Double avg = reviewRepository.findAverageRatingByBookId(bookId);
        return avg == null ? 0.0 : avg;
    }
}
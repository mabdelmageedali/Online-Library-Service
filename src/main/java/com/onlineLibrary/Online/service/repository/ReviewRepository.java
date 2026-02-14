package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // Get reviews of a specific book
    List<Review> findByBookId(Integer bookId);

    // Get reviews written by a user
    List<Review> findByUserId(Integer userId);

    // Get review of user for specific book
    Optional<Review> findByUserIdAndBookId(Integer userId, Integer bookId);

    // Check if user already reviewed this book
    Boolean existsByUserIdAndBookId(Integer userId, Integer bookId);

    // Remove user's review of a book
    void deleteByUserIdAndBookId(Integer userId, Integer bookId);

    // Calculate average rating of a book
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book.id = :bookId")
    Double findAverageRatingByBookId(@Param("bookId") Integer bookId);
}


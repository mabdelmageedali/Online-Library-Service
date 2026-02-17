package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByBookId(Integer bookId);

    List<Review> findByUserId(Integer userId);

    Optional<Review> findByUserIdAndBookId(Integer userId, Integer bookId);

    Boolean existsByUserIdAndBookId(Integer userId, Integer bookId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.user.id = :userId AND r.book.id = :bookId")
    void deleteByUserIdAndBookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book.id = :bookId")
    Double findAverageRatingByBookId(@Param("bookId") Integer bookId);

    long countByBookId(Integer bookId);

    @Query("SELECT r.book.id, AVG(r.rating) as avgRating FROM Review r " +
            "GROUP BY r.book.id " +
            "HAVING AVG(r.rating) >= :minRating " +
            "ORDER BY avgRating DESC")
    List<Object[]> findTopRatedBooks(@Param("minRating") Double minRating);
}
package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    // Get all favorites of user
    List<Favorite> findByUserId(Integer userId);

    // Get favorite by user and book
    Optional<Favorite> findByUserIdAndBookId(Integer userId, Integer bookId);

    // Check if book is in user's favorites
    Boolean existsByUserIdAndBookId(Integer userId, Integer bookId);

    // Remove book from favorites (with proper annotations)
    @Transactional
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.user.id = :userId AND f.book.id = :bookId")
    void deleteByUserIdAndBookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    // Count favorites (useful for profile stats)
    long countByUserId(Integer userId);

    // Get all users who favorited a book (bonus)
    List<Favorite> findByBookId(Integer bookId);
}

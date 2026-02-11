package com.onlineLibrary.Online.Library.repository;

import com.onlineLibrary.Online.Library.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    // Get all favorites of user
    List<Favorite> findByUserId(Integer userId);

    // Check if book is in user's favorites
    Boolean existsByUserIdAndBookId(Integer userId, Integer bookId);

    // Remove book from favorites
    void deleteByUserIdAndBookId(Integer userId, Integer bookId);

    // Count favorites (optional, useful for profile stats)
    long countByUserId(Integer userId);
}

package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.favorite.FavoriteRequestDTO;
import com.onlineLibrary.Online.service.dto.favorite.FavoriteResponseDTO;

import java.util.List;

public interface FavoriteService {

    // Add book to user's favorites
    FavoriteResponseDTO addToFavorites(Integer userId, FavoriteRequestDTO dto);

    // Remove book from user's favorites
    void removeFromFavorites(Integer userId, Integer bookId);

    // Get all favorite books of user
    List<FavoriteResponseDTO> getUserFavorites(Integer userId);

    // Check if book is in user's favorites
    Boolean isBookFavorite(Integer userId, Integer bookId);
}
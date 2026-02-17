package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.favorite.FavoriteRequestDTO;
import com.onlineLibrary.Online.service.dto.favorite.FavoriteResponseDTO;

import java.util.List;

public interface FavoriteService {

    FavoriteResponseDTO addToFavorites(Integer userId, FavoriteRequestDTO dto);

    void removeFromFavorites(Integer userId, Integer bookId);

    List<FavoriteResponseDTO> getUserFavorites(Integer userId);

    Boolean isBookFavorite(Integer userId, Integer bookId);
}
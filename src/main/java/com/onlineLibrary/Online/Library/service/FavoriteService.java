package com.onlineLibrary.Online.Library.service;

import com.onlineLibrary.Online.Library.entity.Favorite;

import java.util.List;

public interface FavoriteService {

    // Add book to user's favorites
    Favorite addToFavorites(Integer userId, Integer bookId);

    // Remove book from user's favorites
    void removeFromFavorites(Integer userId, Integer bookId);

    // Get all favorite books of user
    List<Favorite> getUserFavorites(Integer userId);

    // Check if book is in user's favorites
    Boolean isBookFavorite(Integer userId, Integer bookId);
}

package com.onlineLibrary.Online.Library.service.impl;

import com.onlineLibrary.Online.Library.entity.Book;
import com.onlineLibrary.Online.Library.entity.Favorite;
import com.onlineLibrary.Online.Library.entity.User;
import com.onlineLibrary.Online.Library.repository.BookRepository;
import com.onlineLibrary.Online.Library.repository.FavoriteRepository;
import com.onlineLibrary.Online.Library.repository.UserRepository;
import com.onlineLibrary.Online.Library.service.FavoriteService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public Favorite addToFavorites(Integer userId, Integer bookId) {

        if (favoriteRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new RuntimeException("Already in favorites");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setBook(book);

        return favoriteRepository.save(favorite);
    }

    @Override
    public void removeFromFavorites(Integer userId, Integer bookId) {

        if (!favoriteRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new RuntimeException("Favorite not found");
        }

        favoriteRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    @Override
    public List<Favorite> getUserFavorites(Integer userId) {

        return favoriteRepository.findByUserId(userId);
    }

    @Override
    public Boolean isBookFavorite(Integer userId, Integer bookId) {

        return favoriteRepository.existsByUserIdAndBookId(userId, bookId);
    }
}

package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.entity.Book;
import com.onlineLibrary.Online.service.entity.Favorite;
import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.repository.BookRepository;
import com.onlineLibrary.Online.service.repository.FavoriteRepository;
import com.onlineLibrary.Online.service.repository.UserRepository;
import com.onlineLibrary.Online.service.service.FavoriteService;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public Favorite addToFavorites(Integer userId, Integer bookId) {

        if (favoriteRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new BadRequestException("Already in favorites");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setBook(book);

        return favoriteRepository.save(favorite);
    }

    @Override
    public void removeFromFavorites(Integer userId, Integer bookId) {

        if (!favoriteRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new NotFoundException("Favorite not found");
        }

        favoriteRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Favorite> getUserFavorites(Integer userId) {

        return favoriteRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isBookFavorite(Integer userId, Integer bookId) {

        return favoriteRepository.existsByUserIdAndBookId(userId, bookId);
    }
}
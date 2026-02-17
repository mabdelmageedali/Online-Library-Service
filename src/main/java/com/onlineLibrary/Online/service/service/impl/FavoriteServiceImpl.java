package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.dto.favorite.FavoriteRequestDTO;
import com.onlineLibrary.Online.service.dto.favorite.FavoriteResponseDTO;
import com.onlineLibrary.Online.service.entity.Book;
import com.onlineLibrary.Online.service.entity.Favorite;
import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.mapper.FavoriteMapper;
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
    private final FavoriteMapper favoriteMapper;

    @Override
    public FavoriteResponseDTO addToFavorites(Integer userId, FavoriteRequestDTO dto) {
        if (favoriteRepository.existsByUserIdAndBookId(userId, dto.getBookId())) {
            throw new BadRequestException("Book already in favorites");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + dto.getBookId()));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setBook(book);

        Favorite savedFavorite = favoriteRepository.save(favorite);
        return favoriteMapper.toResponseDTO(savedFavorite);
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
    public List<FavoriteResponseDTO> getUserFavorites(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }

        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return favoriteMapper.toResponseDTOList(favorites);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isBookFavorite(Integer userId, Integer bookId) {
        return favoriteRepository.existsByUserIdAndBookId(userId, bookId);
    }
}
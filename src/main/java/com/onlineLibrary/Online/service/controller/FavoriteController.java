package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.dto.favorite.FavoriteRequestDTO;
import com.onlineLibrary.Online.service.dto.favorite.FavoriteResponseDTO;
import com.onlineLibrary.Online.service.service.FavoriteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    // Add book to favorites
    @PostMapping("/user/{userId}")
    public ResponseEntity<FavoriteResponseDTO> addToFavorites(
            @PathVariable Integer userId,
            @Valid @RequestBody FavoriteRequestDTO dto) {
        FavoriteResponseDTO response = favoriteService.addToFavorites(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Remove book from favorites
    @DeleteMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<Void> removeFromFavorites(
            @PathVariable Integer userId,
            @PathVariable Integer bookId) {
        favoriteService.removeFromFavorites(userId, bookId);
        return ResponseEntity.noContent().build();
    }

    // Get user favorites
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteResponseDTO>> getUserFavorites(@PathVariable Integer userId) {
        return ResponseEntity.ok(favoriteService.getUserFavorites(userId));
    }

    // Check if book is favorite
    @GetMapping("/check/user/{userId}/book/{bookId}")
    public ResponseEntity<Boolean> isBookFavorite(
            @PathVariable Integer userId,
            @PathVariable Integer bookId) {
        Boolean result = favoriteService.isBookFavorite(userId, bookId);
        return ResponseEntity.ok(result);
    }
}
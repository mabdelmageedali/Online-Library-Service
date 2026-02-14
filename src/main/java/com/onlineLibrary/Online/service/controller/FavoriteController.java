package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.entity.Favorite;
import com.onlineLibrary.Online.service.service.FavoriteService;

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
    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<Favorite> addToFavorites(
            @PathVariable Integer userId,
            @PathVariable Integer bookId
    ) {

        Favorite favorite =
                favoriteService.addToFavorites(userId, bookId);

        return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
    }

    // Remove book from favorites
    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity<Void> removeFromFavorites(
            @PathVariable Integer userId,
            @PathVariable Integer bookId
    ) {

        favoriteService.removeFromFavorites(userId, bookId);

        return ResponseEntity.noContent().build();
    }

    // Get user favorites
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favorite>> getUserFavorites(
            @PathVariable Integer userId
    ) {

        return ResponseEntity.ok(
                favoriteService.getUserFavorites(userId)
        );
    }

    // Check if book is favorite
    @GetMapping("/check/{userId}/{bookId}")
    public ResponseEntity<Boolean> isBookFavorite(
            @PathVariable Integer userId,
            @PathVariable Integer bookId
    ) {

        Boolean result =
                favoriteService.isBookFavorite(userId, bookId);

        return ResponseEntity.ok(result);
    }
}

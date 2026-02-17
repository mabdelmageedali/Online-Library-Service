package com.onlineLibrary.Online.service.dto.user;

import com.onlineLibrary.Online.service.dto.profile.ProfileResponseDTO;

import java.time.LocalDateTime;

public record UserDetailsDTO(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String biography,
        LocalDateTime birthDate,
        LocalDateTime joinDate,
        Integer totalFavorites,
        Integer totalReviews
) {
}

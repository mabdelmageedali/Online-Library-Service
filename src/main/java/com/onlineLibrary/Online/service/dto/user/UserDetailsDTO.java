package com.onlineLibrary.Online.service.dto.user;

import com.onlineLibrary.Online.service.dto.profile.ProfileResponseDTO;

import java.time.LocalDateTime;

public record UserDetailsDTO(
        Integer id,
        String email,
        String phoneNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        ProfileResponseDTO useProfileData,
        Integer totalFavorites,
        Integer totalReviews
) {
}

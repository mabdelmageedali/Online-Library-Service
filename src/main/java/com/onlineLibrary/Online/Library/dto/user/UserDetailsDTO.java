package com.onlineLibrary.Online.Library.dto.user;

import com.onlineLibrary.Online.Library.dto.profile.ProfileResponseDTO;

public record UserDetailsDTO(
        Integer id,
        String email,
        String phoneNumber,
        String createdAt,
        String updatedAt,
        ProfileResponseDTO useProfileData,
        int totalFavorites,
        int totalReviews
) {
}

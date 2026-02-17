package com.onlineLibrary.Online.service.dto.profile;

import com.onlineLibrary.Online.service.dto.user.UserSummaryDTO;

import java.time.LocalDateTime;


public record ProfileResponseDTO(
        Integer id,
        UserSummaryDTO user,
        String biography,
        LocalDateTime birthDate,
        LocalDateTime joinDate
) {
}

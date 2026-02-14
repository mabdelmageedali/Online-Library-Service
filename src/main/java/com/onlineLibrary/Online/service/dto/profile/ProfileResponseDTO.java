package com.onlineLibrary.Online.service.dto.profile;

import java.time.LocalDateTime;


public record ProfileResponseDTO(
        Integer id,
        String firstName,
        String lastName,
        String biography,
        LocalDateTime birthDate,
        LocalDateTime joinDate
) {
}

package com.onlineLibrary.Online.service.dto.review;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Integer id,
        int rating,
        String comment,
        LocalDateTime createdAt
) {
}

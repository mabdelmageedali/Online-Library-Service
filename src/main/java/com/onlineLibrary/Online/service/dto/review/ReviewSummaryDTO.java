package com.onlineLibrary.Online.service.dto.review;

public record ReviewSummaryDTO(
        Integer id,
        int rating,
        String comment
) {
}

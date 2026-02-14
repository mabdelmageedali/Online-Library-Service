package com.onlineLibrary.Online.service.dto.book;

public record BookSummaryDTO(
        Integer id,
        String title,
        String language,
        Integer pagesCount
) {
}

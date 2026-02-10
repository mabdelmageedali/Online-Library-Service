package com.onlineLibrary.Online.Library.dto.book;

public record BookSummaryDTO(
        Integer id,
        String title,
        String language,
        int pagesCount
) {
}

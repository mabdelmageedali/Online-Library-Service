package com.onlineLibrary.Online.service.dto.book;

import com.onlineLibrary.Online.service.enums.FileType;

public record BookSummaryDTO(
        Integer id,
        String title,
        String language,
        Integer pagesCount,
        FileType fileType
) {
}

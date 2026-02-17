package com.onlineLibrary.Online.service.dto.book;

import com.onlineLibrary.Online.service.dto.author.AuthorSummaryDTO;
import com.onlineLibrary.Online.service.enums.FileType;

import java.time.LocalDateTime;
import java.util.List;

public record BookResponseDTO(
        Integer id,
        String title,
        String description,
        String language,
        Integer pagesCount,
        LocalDateTime publishDate,
        LocalDateTime updateDate,
        List<AuthorSummaryDTO> bookAuthors,
        FileType fileType,
        Long fileSize,
        String downloadUrl
) {
}

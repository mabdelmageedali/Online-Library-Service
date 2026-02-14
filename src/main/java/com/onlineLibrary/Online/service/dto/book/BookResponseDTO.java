package com.onlineLibrary.Online.service.dto.book;

import com.onlineLibrary.Online.service.dto.author.AuthorSummaryDTO;
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
        List<AuthorSummaryDTO> bookAuthors
        // add CategorySummaryDTO here
) {
}

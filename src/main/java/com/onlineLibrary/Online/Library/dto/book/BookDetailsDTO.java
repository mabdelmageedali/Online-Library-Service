package com.onlineLibrary.Online.Library.dto.book;

import com.onlineLibrary.Online.Library.dto.author.AuthorSummaryDTO;


import java.time.LocalDateTime;
import java.util.List;

public record BookDetailsDTO(
        Integer id,
        String title,
        String description,
        String language,
        Integer pagesCount,
        LocalDateTime publishDate,
        LocalDateTime updateDate,
        List<AuthorSummaryDTO> authors
) {
}

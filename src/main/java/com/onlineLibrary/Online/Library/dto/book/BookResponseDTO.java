package com.onlineLibrary.Online.Library.dto.book;

import com.onlineLibrary.Online.Library.dto.author.AuthorSummaryDTO;
import java.time.LocalDate;
import java.util.List;

public record BookResponseDTO(
        Integer id,
        String title,
        String description,
        String language,
        int pagesCount,
        LocalDate publishDate,
        LocalDate updateDate,
        List<AuthorSummaryDTO> bookAuthors
        // add CategorySummaryDTO here
) {
}

package com.onlineLibrary.Online.Library.dto.category;

import com.onlineLibrary.Online.Library.dto.book.BookSummaryDTO;

import java.util.List;

public record CategoryResponseDTO(
        Integer id,
        String categoryName,
        String categoryDescription,
        List<BookSummaryDTO> books
) {
}

package com.onlineLibrary.Online.service.dto.category;

import com.onlineLibrary.Online.service.dto.book.BookSummaryDTO;

import java.util.List;

public record CategoryResponseDTO(
        Integer id,
        String categoryName,
        String categoryDescription,
        List<BookSummaryDTO> books
) {
}

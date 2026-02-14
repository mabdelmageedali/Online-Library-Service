package com.onlineLibrary.Online.service.dto.favorite;


import com.onlineLibrary.Online.service.dto.book.BookSummaryDTO;
import java.time.LocalDateTime;

public record FavoriteResponseDTO(
        Integer id,
        LocalDateTime addedAt,
        BookSummaryDTO book
) {
}

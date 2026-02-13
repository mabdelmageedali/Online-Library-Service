package com.onlineLibrary.Online.Library.dto.favorite;


import com.onlineLibrary.Online.Library.dto.book.BookSummaryDTO;
import java.time.LocalDateTime;

public record FavoriteResponseDTO(
        Integer id,
        LocalDateTime addedAt,
        BookSummaryDTO book
) {
}

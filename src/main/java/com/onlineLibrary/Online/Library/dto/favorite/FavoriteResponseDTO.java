package com.onlineLibrary.Online.Library.dto.favorite;


import com.onlineLibrary.Online.Library.dto.book.BookSummaryDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;

public record FavoriteResponseDTO(
        Integer id,
        LocalDate addedAt,
        BookSummaryDTO book
) {
}

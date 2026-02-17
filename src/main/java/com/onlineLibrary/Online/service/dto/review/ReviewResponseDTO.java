package com.onlineLibrary.Online.service.dto.review;

import com.onlineLibrary.Online.service.dto.book.BookSummaryDTO;
import com.onlineLibrary.Online.service.dto.user.UserSummaryDTO;
import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Integer id,
        Integer rating,
        String comment,
        LocalDateTime createdAt,
        UserSummaryDTO user,
        BookSummaryDTO book
) {
}

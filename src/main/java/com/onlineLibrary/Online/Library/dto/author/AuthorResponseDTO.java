package com.onlineLibrary.Online.Library.dto.author;

import java.time.LocalDate;

public record AuthorResponseDTO(
        Integer id,
        String authorName,
        String biography,
        LocalDate birthDate,
        LocalDate deathDate
) {
}

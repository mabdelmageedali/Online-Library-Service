package com.onlineLibrary.Online.Library.dto.author;


import java.time.LocalDateTime;

public record AuthorResponseDTO(
        Integer id,
        String authorName,
        String biography,
        LocalDateTime birthDate,
        LocalDateTime deathDate
) {
}

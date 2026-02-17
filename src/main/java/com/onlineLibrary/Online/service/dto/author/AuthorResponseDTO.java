package com.onlineLibrary.Online.service.dto.author;


import java.time.LocalDateTime;

public record AuthorResponseDTO(
        Integer id,
        String authorName,
        String bio,
        LocalDateTime birthDate,
        LocalDateTime deathDate
) {
}

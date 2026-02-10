package com.onlineLibrary.Online.Library.dto.profile;

import java.time.LocalDate;


public record ProfileResponseDTO(
        Integer id,
        String firstName,
        String lastName,
        String biography,
        LocalDate birthDate,
        LocalDate joinDate
) {
}

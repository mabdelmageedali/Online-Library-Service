package com.onlineLibrary.Online.service.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRequestDTO {

    @NotBlank(message = "Author name is required")
    private String authorName;

    @NotBlank(message = "Author biography is required")
    private String biography;

    @NotNull(message = "Author birth date is required")
    @Past(message = "Author birth date should be valid")
    private LocalDateTime birthDate;

    @Past(message = "Author death date should be valid")
    private LocalDateTime deathDate;

}

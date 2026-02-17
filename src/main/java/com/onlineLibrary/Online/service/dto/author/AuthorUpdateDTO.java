package com.onlineLibrary.Online.service.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorUpdateDTO {

    @NotBlank(message = "Author name cannot be empty")
    private String authorName;

    @NotBlank(message = "Biography cannot be empty")
    private String bio;

    @Past(message = "Author birth date should be valid")
    private LocalDateTime birthDate;

    @Past(message = "Author death date should be valid")
    private LocalDateTime deathDate;

}

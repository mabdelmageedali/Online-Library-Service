package com.onlineLibrary.Online.service.dto.author;

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

    private String authorName;

    private String biography;

    @Past(message = "Author birth date should be valid")
    private LocalDateTime birthDate;

    @Past(message = "Author death date should be valid")
    private LocalDateTime deathDate;

}

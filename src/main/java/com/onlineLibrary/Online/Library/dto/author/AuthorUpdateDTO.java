package com.onlineLibrary.Online.Library.dto.author;

import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorUpdateDTO {

    private String authorName;

    private String biography;

    @Past(message = "Author birth date should be valid")
    private LocalDate birthDate;

    @Past(message = "Author death date should be valid")
    private LocalDate deathDate;

}

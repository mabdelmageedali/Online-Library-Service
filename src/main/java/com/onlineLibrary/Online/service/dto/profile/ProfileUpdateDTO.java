package com.onlineLibrary.Online.service.dto.profile;

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
@Setter
@Getter
public class ProfileUpdateDTO {

    private String biography;

    @Past(message = "Birth date must be in the past")
    @NotNull(message = "Birth date is required")
    private LocalDateTime birthDate;

}

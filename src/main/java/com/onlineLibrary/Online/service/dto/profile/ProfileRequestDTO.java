package com.onlineLibrary.Online.service.dto.profile;

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
public class ProfileRequestDTO {

    private String biography;

    @NotNull(message = "Birth date is required")
    @Past
    private LocalDateTime birthDate;

}

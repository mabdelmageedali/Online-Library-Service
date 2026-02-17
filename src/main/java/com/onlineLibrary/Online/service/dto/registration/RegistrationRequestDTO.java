package com.onlineLibrary.Online.service.dto.registration;

import com.onlineLibrary.Online.service.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^01[0125][0-9]{8}$",
            message = "Invalid phone number"
    )
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
    )
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    /**
     * Role must be either ROLE_USER or ROLE_AUTHOR.
     * Defaults to ROLE_USER if not supplied.
     */
    @NotNull(message = "Role is required (ROLE_USER or ROLE_AUTHOR)")
    private Role role;

    /**
     * Required because a Profile is auto-created during registration.
     * Must be a past date.
     */
    @NotNull(message = "Birth date is required for profile creation")
    @Past(message = "Birth date must be in the past")
    private LocalDateTime birthDate;

    /** Optional biography — can be added/edited later via the Profile page. */
    private String biography;
}

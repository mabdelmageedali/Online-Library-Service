package com.onlineLibrary.Online.service.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserUpdateDTO {

    private String firstName;

    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(
            regexp = "^01[0125][0-9]{8}$",
            message = "Invalid phone number"
    )
    private String phoneNumber;

    @Size(min = 6, message = "Password should be at least 6 characters")
    private String currentPassword;

    @Size(min = 6, message = "New password must be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
    )
    private String newPassword;
}

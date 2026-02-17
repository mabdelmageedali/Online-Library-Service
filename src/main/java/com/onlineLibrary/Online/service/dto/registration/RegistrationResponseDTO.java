package com.onlineLibrary.Online.service.dto.registration;

import com.onlineLibrary.Online.service.enums.Role;
import java.time.LocalDateTime;


public record RegistrationResponseDTO(


        Integer userId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Role   role,
        LocalDateTime registeredAt,


        Integer profileId,
        String  biography,
        LocalDateTime birthDate,
        LocalDateTime joinDate,


        Integer authorId
) {}

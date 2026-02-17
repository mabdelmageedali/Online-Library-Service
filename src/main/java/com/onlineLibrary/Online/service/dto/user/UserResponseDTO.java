package com.onlineLibrary.Online.service.dto.user;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDateTime createdAt
) {}

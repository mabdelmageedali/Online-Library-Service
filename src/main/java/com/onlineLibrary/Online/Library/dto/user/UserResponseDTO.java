package com.onlineLibrary.Online.Library.dto.user;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Integer id,
        String email,
        String phoneNumber,
        LocalDateTime createdAt
) {}

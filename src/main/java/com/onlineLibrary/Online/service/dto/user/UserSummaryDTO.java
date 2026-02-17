package com.onlineLibrary.Online.service.dto.user;

public record UserSummaryDTO(
        Integer id,
        String firstName,
        String lastName,
        String email
) {
}

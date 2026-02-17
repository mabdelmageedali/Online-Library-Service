package com.onlineLibrary.Online.service.dto.profile;

import com.onlineLibrary.Online.service.dto.user.UserSummaryDTO;
import com.onlineLibrary.Online.service.enums.Role;
import java.time.LocalDateTime;

public record ProfileWithRoleDTO(
        Integer        id,
        UserSummaryDTO user,
        Role           role,
        String         biography,
        LocalDateTime  birthDate,
        LocalDateTime  joinDate
) {}

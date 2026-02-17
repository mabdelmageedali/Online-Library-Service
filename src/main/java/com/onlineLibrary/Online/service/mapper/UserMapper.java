package com.onlineLibrary.Online.service.mapper;

import com.onlineLibrary.Online.service.dto.user.*;
import com.onlineLibrary.Online.service.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Entity -> DTOs
    UserResponseDTO toResponseDTO(User user);

    UserSummaryDTO toSummaryDTO(User user);

    @Mapping(target = "biography", source = "profile.biography")
    @Mapping(target = "birthDate", source = "profile.birthDate")
    @Mapping(target = "joinDate", source = "profile.joinDate")
    @Mapping(target = "totalFavorites", expression = "java(user.getFavorites() != null ? user.getFavorites().size() : 0)")
    @Mapping(target = "totalReviews", expression = "java(user.getReviews() != null ? user.getReviews().size() : 0)")
    UserDetailsDTO toDetailsDTO(User user);

    List<UserResponseDTO> toResponseDTOList(List<User> users);

    List<UserSummaryDTO> toSummaryDTOList(List<User> users);

    // DTOs -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true) // Will be encoded separately
    User toEntity(UserRegistrationDTO dto);

    // Update Entity from DTO (only non-null fields)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // Password updated separately
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateEntityFromDTO(UserUpdateDTO dto, @MappingTarget User user);
}


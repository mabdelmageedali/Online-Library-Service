package com.onlineLibrary.Online.service.mapper;

import com.onlineLibrary.Online.service.dto.profile.ProfileRequestDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileResponseDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileUpdateDTO;
import com.onlineLibrary.Online.service.entity.Profile;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProfileMapper {

    // Entity -> DTOs
    @Mapping(target = "user", source = "user")
    ProfileResponseDTO toResponseDTO(Profile profile);

    List<ProfileResponseDTO> toResponseDTOList(List<Profile> profiles);

    // DTOs -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "joinDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    Profile toEntity(ProfileRequestDTO dto);

    // Update Entity from DTO (only non-null fields)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "joinDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDTO(ProfileUpdateDTO dto, @MappingTarget Profile profile);
}

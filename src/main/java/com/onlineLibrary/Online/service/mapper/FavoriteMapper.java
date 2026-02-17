package com.onlineLibrary.Online.service.mapper;

import com.onlineLibrary.Online.service.dto.favorite.FavoriteRequestDTO;
import com.onlineLibrary.Online.service.dto.favorite.FavoriteResponseDTO;
import com.onlineLibrary.Online.service.entity.Favorite;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface FavoriteMapper {

    FavoriteResponseDTO toResponseDTO(Favorite favorite);

    List<FavoriteResponseDTO> toResponseDTOList(List<Favorite> favorites);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addedAt", ignore = true)
    @Mapping(target = "user", ignore = true) // Will be set from authenticated user
    @Mapping(target = "book", ignore = true) // Will be set from bookId
    Favorite toEntity(FavoriteRequestDTO dto);
}

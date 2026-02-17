package com.onlineLibrary.Online.service.mapper;

import com.onlineLibrary.Online.service.dto.author.AuthorRequestDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorResponseDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorSummaryDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorUpdateDTO;
import com.onlineLibrary.Online.service.entity.Author;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {


    @Mapping(target = "authorName", source = "authorName")
    @Mapping(target = "bio", source = "bio")
    AuthorResponseDTO toResponseDTO(Author author);

    @Mapping(target = "authorName", source = "authorName")
    @Mapping(target = "bio", source = "bio")
    AuthorSummaryDTO toSummaryDTO(Author author);

    List<AuthorResponseDTO> toResponseDTOList(List<Author> authors);

    List<AuthorSummaryDTO> toSummaryDTOList(List<Author> authors);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Author toEntity(AuthorRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateEntityFromDTO(AuthorUpdateDTO dto, @MappingTarget Author author);
}
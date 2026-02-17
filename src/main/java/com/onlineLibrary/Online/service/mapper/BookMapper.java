package com.onlineLibrary.Online.service.mapper;

import com.onlineLibrary.Online.service.dto.book.BookRequestDTO;
import com.onlineLibrary.Online.service.dto.book.BookResponseDTO;
import com.onlineLibrary.Online.service.dto.book.BookSummaryDTO;
import com.onlineLibrary.Online.service.dto.book.BookUpdateDTO;
import com.onlineLibrary.Online.service.entity.Book;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {

    // Entity -> DTOs
    @Mapping(target = "downloadUrl", ignore = true) // Will be set manually in service
    BookResponseDTO toResponseDTO(Book book);

    BookSummaryDTO toSummaryDTO(Book book);

    List<BookResponseDTO> toResponseDTOList(List<Book> books);

    List<BookSummaryDTO> toSummaryDTOList(List<Book> books);

    // DTOs -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "filePath", ignore = true)
    @Mapping(target = "fileType", ignore = true)
    @Mapping(target = "fileSize", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Book toEntity(BookRequestDTO dto);

    // Update Entity from DTO (only non-null fields)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "filePath", ignore = true)
    @Mapping(target = "fileType", ignore = true)
    @Mapping(target = "fileSize", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    void updateEntityFromDTO(BookUpdateDTO dto, @MappingTarget Book book);
}
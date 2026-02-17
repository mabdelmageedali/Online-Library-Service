package com.onlineLibrary.Online.service.mapper;

import com.onlineLibrary.Online.service.dto.category.CategoryRequestDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryResponseDTO;
import com.onlineLibrary.Online.service.dto.category.CategorySummaryDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryUpdateDTO;
import com.onlineLibrary.Online.service.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Entity -> DTOs
    @Mapping(target = "books", ignore = true) // Avoid circular dependency
    CategoryResponseDTO toResponseDTO(Category category);

    CategorySummaryDTO toSummaryDTO(Category category);

    List<CategoryResponseDTO> toResponseDTOList(List<Category> categories);

    List<CategorySummaryDTO> toSummaryDTOList(List<Category> categories);

    // DTOs -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Category toEntity(CategoryRequestDTO dto);

    // Update Entity from DTO (only non-null fields)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateEntityFromDTO(CategoryUpdateDTO dto, @MappingTarget Category category);
}
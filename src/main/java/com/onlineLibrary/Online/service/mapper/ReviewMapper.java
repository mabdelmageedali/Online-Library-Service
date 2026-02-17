package com.onlineLibrary.Online.service.mapper;

import com.onlineLibrary.Online.service.dto.review.ReviewRequestDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewResponseDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewSummaryDTO;
import com.onlineLibrary.Online.service.dto.review.ReviewUpdateDTO;
import com.onlineLibrary.Online.service.entity.Review;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BookMapper.class})
public interface ReviewMapper {

    ReviewResponseDTO toResponseDTO(Review review);

    ReviewSummaryDTO toSummaryDTO(Review review);

    List<ReviewResponseDTO> toResponseDTOList(List<Review> reviews);

    List<ReviewSummaryDTO> toSummaryDTOList(List<Review> reviews);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true) // Will be set from authenticated user
    @Mapping(target = "book", ignore = true) // Will be set from bookId
    Review toEntity(ReviewRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "book", ignore = true)
    void updateEntityFromDTO(ReviewUpdateDTO dto, @MappingTarget Review review);
}

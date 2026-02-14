package com.onlineLibrary.Online.service.dto.book;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BookRequestDTO {

    @NotBlank(message = "Book title is required")
    private String title;

    private String description;

    @NotBlank(message = "Book language is required")
    private String language;

    @Min(value = 1, message = "Pages should be at least 1")
    @NotNull(message = "Pages count is required")
    private Integer pagesCount;

    @PastOrPresent(message = "Publish date can not be in future")
    @NotNull(message = "Publish date is required")
    private LocalDateTime publishDate;


    @NotEmpty(message = "At least one author is required")
    private List<Integer> authorIds;

    private List<Integer> categoryIds;

}

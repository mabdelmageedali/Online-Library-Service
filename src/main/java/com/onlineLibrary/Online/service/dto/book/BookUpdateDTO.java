package com.onlineLibrary.Online.service.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookUpdateDTO {

    @NotBlank(message = "Book title cannot be empty")
    private String title;

    private String description;

    @NotBlank(message = "Book language cannot be empty")
    private String language;

    @Min(value = 1, message = "Pages should be at least 1")
    private Integer pagesCount;

    @PastOrPresent(message = "Publish date cannot be in the future")
    private LocalDateTime publishDate;

    private MultipartFile bookFile;
}

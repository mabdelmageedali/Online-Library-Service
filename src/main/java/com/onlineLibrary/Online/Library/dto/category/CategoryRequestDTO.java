package com.onlineLibrary.Online.Library.dto.category;

import com.onlineLibrary.Online.Library.dto.book.BookSummaryDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryRequestDTO {

    @NotBlank(message = "Category name is required")
    private String categoryName;

    private String categoryDescription;

}

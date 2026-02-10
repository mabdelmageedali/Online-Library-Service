package com.onlineLibrary.Online.Library.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryUpdateDTO {

    @NotBlank(message = "Category name is required")
    private String categoryName;

    private String categoryDescription;
}

package com.onlineLibrary.Online.service.dto.category;

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

    private String categoryName;

    private String categoryDescription;
}

package com.onlineLibrary.Online.service.dto.favorite;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FavoriteRequestDTO {
    @NotNull(message = "Book ID is required")
    Integer bookId;

}

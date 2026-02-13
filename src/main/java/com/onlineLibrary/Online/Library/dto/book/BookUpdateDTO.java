package com.onlineLibrary.Online.Library.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookUpdateDTO {

    private String title;
    private String description;
    private String language;
    private Integer pagesCount;
    private LocalDateTime publishDate;

}

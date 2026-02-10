package com.onlineLibrary.Online.Library.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookUpdateDTO {

    private String title;
    private String description;
    private String language;
    private int pagesCount;
    private LocalDate publishDate;

}

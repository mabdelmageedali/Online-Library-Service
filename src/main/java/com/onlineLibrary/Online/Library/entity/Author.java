package com.onlineLibrary.Online.Library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private String biography;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    @Column(nullable = true)
    private LocalDateTime deathDate;

    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<Book> books;


}

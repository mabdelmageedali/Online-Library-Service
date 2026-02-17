package com.onlineLibrary.Online.service.entity;

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
    private String bio;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    private LocalDateTime deathDate;

    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<Book> books;

}

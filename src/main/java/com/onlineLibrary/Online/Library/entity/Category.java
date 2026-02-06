package com.onlineLibrary.Online.Library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = true)
    private String categoryDescription;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Book> books;
}

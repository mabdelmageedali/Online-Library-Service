package com.onlineLibrary.Online.Library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true)
    private String biography;

    @Column(nullable = false)
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime joinDate;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false) // foreign key
    @JsonIgnore
    private User user;

}

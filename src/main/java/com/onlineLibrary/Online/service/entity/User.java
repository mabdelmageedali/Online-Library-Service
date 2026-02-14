package com.onlineLibrary.Online.service.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false , unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must contain only digits")
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Profile profile;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviews;

}

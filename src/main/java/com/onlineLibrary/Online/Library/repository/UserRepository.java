package com.onlineLibrary.Online.Library.repository;

import com.onlineLibrary.Online.Library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find user by email (used in login)
    Optional<User> findByEmail(String email);

    // Check if email already exists (used in register)
    Boolean existsByEmail(String email);

    // Check if phone number already exists (used in register)
    Boolean existsByPhoneNumber(String phoneNumber);
}

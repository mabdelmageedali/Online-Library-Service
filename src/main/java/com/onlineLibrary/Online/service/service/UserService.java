package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Register
    User register(User user);

    // Login
    User login(String email, String password);

    // Get user by ID
    Optional<User> getUserById(Integer id);

    // Get all users
    List<User> getAllUsers();

    // Update user information
    User updateUser(Integer id, User user);

    // Delete user account
    void deleteUser(Integer id);

    // Check if email is already used
    Boolean isEmailTaken(String email);

    // Check if phone number is already used
    Boolean isPhoneTaken(String phoneNumber);
}

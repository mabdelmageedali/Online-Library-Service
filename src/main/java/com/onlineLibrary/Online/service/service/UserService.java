package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.user.*;

import java.util.List;

public interface UserService {

    // Register new user
    UserResponseDTO register(UserRegistrationDTO dto);

    // Login
    UserResponseDTO login(UserLoginDTO dto);

    // Get user by ID (with full details)
    UserDetailsDTO getUserById(Integer id);

    // Get all users
    List<UserResponseDTO> getAllUsers();

    // Update user information
    UserResponseDTO updateUser(Integer id, UserUpdateDTO dto);

    // Delete user account
    void deleteUser(Integer id);

    // Check if email is already used
    Boolean isEmailTaken(String email);

    // Check if phone number is already used
    Boolean isPhoneTaken(String phoneNumber);
}
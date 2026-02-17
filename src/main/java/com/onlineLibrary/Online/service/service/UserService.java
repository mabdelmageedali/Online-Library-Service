package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.user.*;

import java.util.List;

public interface UserService {

    UserResponseDTO register(UserRegistrationDTO dto);

    UserResponseDTO login(UserLoginDTO dto);

    UserDetailsDTO getUserById(Integer id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Integer id, UserUpdateDTO dto);

    void deleteUser(Integer id);

    Boolean isEmailTaken(String email);

    Boolean isPhoneTaken(String phoneNumber);
}
package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.exception.UnauthorizedException;
import com.onlineLibrary.Online.service.repository.UserRepository;
import com.onlineLibrary.Online.service.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Register
    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new BadRequestException("Phone number already exists");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BadRequestException("Password is required");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // Login
    @Override
    @Transactional(readOnly = true)
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        return user;
    }

    // Get by id
    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Integer id) {

        return userRepository.findById(id);
    }

    // Get all
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // Update
    @Override
    public User updateUser(Integer id, User user) {

        User oldUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("User not found"));

        if (!oldUser.getEmail().equals(user.getEmail())
                && userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        if (!oldUser.getPhoneNumber().equals(user.getPhoneNumber())
                && userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new BadRequestException("Phone number already exists");
        }

        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            oldUser.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }

        return userRepository.save(oldUser);
    }

    // Delete
    @Override
    public void deleteUser(Integer id) {

        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }

    // Check email
    @Override
    @Transactional(readOnly = true)
    public Boolean isEmailTaken(String email) {

        return userRepository.existsByEmail(email);
    }

    // Check phone
    @Override
    @Transactional(readOnly = true)
    public Boolean isPhoneTaken(String phoneNumber) {

        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}
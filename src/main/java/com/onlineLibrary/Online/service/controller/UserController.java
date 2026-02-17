package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.dto.user.*;
import com.onlineLibrary.Online.service.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegistrationDTO dto) {
        UserResponseDTO response = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginDTO dto) {
        UserResponseDTO response = userService.login(dto);
        return ResponseEntity.ok(response);
    }

    // Get user by id (with full details)
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable Integer id) {
        UserDetailsDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    // Get all users (Admin)
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserUpdateDTO dto) {
        UserResponseDTO response = userService.updateUser(id, dto);
        return ResponseEntity.ok(response);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Check if email is taken
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.isEmailTaken(email));
    }

    // Check if phone is taken
    @GetMapping("/check-phone")
    public ResponseEntity<Boolean> checkPhone(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(userService.isPhoneTaken(phoneNumber));
    }
}
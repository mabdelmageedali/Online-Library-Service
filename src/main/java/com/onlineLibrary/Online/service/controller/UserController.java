package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
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
    public ResponseEntity<User> register(@Valid @RequestBody User user) {

        if (userService.isEmailTaken(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        if (userService.isPhoneTaken(user.getPhoneNumber())) {
            throw new BadRequestException("Phone number already exists");
        }

        User savedUser = userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestParam String email,
            @RequestParam String password
    ) {

        User user = userService.login(email, password);

        return ResponseEntity.ok(user);
    }

    // Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {

        User user = userService.getUserById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return ResponseEntity.ok(user);
    }

    // Get all users (Admin)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody User user
    ) {

        User updatedUser = userService.updateUser(id, user);

        return ResponseEntity.ok(updatedUser);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}

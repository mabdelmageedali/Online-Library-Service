package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.dto.profile.ProfileRequestDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileResponseDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileUpdateDTO;
import com.onlineLibrary.Online.service.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // Create profile for user
    @PostMapping("/user/{userId}")
    public ResponseEntity<ProfileResponseDTO> createProfile(
            @PathVariable Integer userId,
            @Valid @RequestBody ProfileRequestDTO dto) {
        ProfileResponseDTO response = profileService.createProfile(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get profile by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Integer userId) {
        ProfileResponseDTO response = profileService.getProfileByUserId(userId);
        return ResponseEntity.ok(response);
    }

    // Update profile
    @PutMapping("/user/{userId}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Integer userId,
            @Valid @RequestBody ProfileUpdateDTO dto) {
        ProfileResponseDTO response = profileService.updateProfile(userId, dto);
        return ResponseEntity.ok(response);
    }

    // Delete profile
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Integer userId) {
        profileService.deleteProfile(userId);
        return ResponseEntity.noContent().build();
    }
}
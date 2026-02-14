package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.entity.Profile;
import com.onlineLibrary.Online.service.exception.NotFoundException;
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
    @PostMapping("/{userId}")
    public ResponseEntity<Profile> createProfile(
            @PathVariable Integer userId,
            @Valid @RequestBody Profile profile
    ) {

        Profile savedProfile =
                profileService.createProfile(userId, profile);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    // Get profile by user id
    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfile(
            @PathVariable Integer userId
    ) {

        Profile profile = profileService.getProfileByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        return ResponseEntity.ok(profile);
    }

    // Update profile
    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable Integer userId,
            @Valid @RequestBody Profile profile
    ) {

        Profile updatedProfile =
                profileService.updateProfile(userId, profile);

        return ResponseEntity.ok(updatedProfile);
    }

    // Delete profile
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(
            @PathVariable Integer userId
    ) {

        profileService.deleteProfile(userId);

        return ResponseEntity.noContent().build();
    }
}

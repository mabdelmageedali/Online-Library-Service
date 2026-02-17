package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.dto.profile.ProfileWithRoleDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileUpdateDTO;
import com.onlineLibrary.Online.service.dto.registration.RegistrationRequestDTO;
import com.onlineLibrary.Online.service.dto.registration.RegistrationResponseDTO;
import com.onlineLibrary.Online.service.service.ProfileService;
import com.onlineLibrary.Online.service.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ProfileService      profileService;

    @PostMapping
    public ResponseEntity<RegistrationResponseDTO> register(
            @Valid @RequestBody RegistrationRequestDTO dto) {

        RegistrationResponseDTO response = registrationService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProfileWithRoleDTO> getProfile(
            @PathVariable Integer userId) {

        ProfileWithRoleDTO response = profileService.getProfileWithRoleByUserId(userId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/profile/{userId}")
    public ResponseEntity<ProfileWithRoleDTO> updateProfile(
            @PathVariable Integer userId,
            @Valid @RequestBody ProfileUpdateDTO dto) {

        profileService.updateProfile(userId, dto);

        ProfileWithRoleDTO response = profileService.getProfileWithRoleByUserId(userId);
        return ResponseEntity.ok(response);
    }
}

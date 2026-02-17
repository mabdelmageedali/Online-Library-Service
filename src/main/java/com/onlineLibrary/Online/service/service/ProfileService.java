package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.profile.ProfileRequestDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileResponseDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileUpdateDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileWithRoleDTO;

public interface ProfileService {

    // Create profile for user
    ProfileResponseDTO createProfile(Integer userId, ProfileRequestDTO dto);

    // Get profile of a user
    ProfileResponseDTO getProfileByUserId(Integer userId);

    /**
     * Get profile of a user enriched with the user's Role.
     * Used by the shared registration/profile page so the UI can adapt
     * its behaviour (e.g. show author-specific sections).
     */
    ProfileWithRoleDTO getProfileWithRoleByUserId(Integer userId);

    // Update profile (biography / birthDate) — reachable from the Profile page
    ProfileResponseDTO updateProfile(Integer userId, ProfileUpdateDTO dto);

    // Delete profile
    void deleteProfile(Integer userId);
}
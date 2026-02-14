package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.entity.Profile;

import java.util.Optional;

public interface ProfileService {

    // Create profile for user
    Profile createProfile(Integer userId, Profile profile);

    // Get profile of a user
    Optional<Profile> getProfileByUserId(Integer userId);

    // Update profile
    Profile updateProfile(Integer userId, Profile profile);

    // Delete profile
    void deleteProfile(Integer userId);
}

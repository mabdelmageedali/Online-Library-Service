package com.onlineLibrary.Online.Library.service.impl;

import com.onlineLibrary.Online.Library.entity.Profile;
import com.onlineLibrary.Online.Library.entity.User;
import com.onlineLibrary.Online.Library.repository.ProfileRepository;
import com.onlineLibrary.Online.Library.repository.UserRepository;
import com.onlineLibrary.Online.Library.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public Profile createProfile(Integer userId, Profile profile) {

        if (profileRepository.existsByUserId(userId)) {
            throw new RuntimeException("Profile already exists");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setUser(user);

        return profileRepository.save(profile);
    }

    @Override
    public Optional<Profile> getProfileByUserId(Integer userId) {

        return profileRepository.findByUserId(userId);
    }

    @Override
    public Profile updateProfile(Integer userId, Profile profile) {

        Profile oldProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        oldProfile.setFirstName(profile.getFirstName());
        oldProfile.setLastName(profile.getLastName());
        oldProfile.setBiography(profile.getBiography());
        oldProfile.setBirthDate(profile.getBirthDate());

        return profileRepository.save(oldProfile);
    }

    @Override
    public void deleteProfile(Integer userId) {

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profileRepository.delete(profile);
    }
}

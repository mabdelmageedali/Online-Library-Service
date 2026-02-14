package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.entity.Profile;
import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.repository.ProfileRepository;
import com.onlineLibrary.Online.service.repository.UserRepository;
import com.onlineLibrary.Online.service.service.ProfileService;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
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

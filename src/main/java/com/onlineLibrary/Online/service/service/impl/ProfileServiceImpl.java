package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.dto.profile.ProfileRequestDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileResponseDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileUpdateDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileWithRoleDTO;
import com.onlineLibrary.Online.service.dto.user.UserSummaryDTO;
import com.onlineLibrary.Online.service.entity.Profile;
import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.mapper.ProfileMapper;
import com.onlineLibrary.Online.service.repository.ProfileRepository;
import com.onlineLibrary.Online.service.repository.UserRepository;
import com.onlineLibrary.Online.service.service.ProfileService;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileResponseDTO createProfile(Integer userId, ProfileRequestDTO dto) {
        if (profileRepository.existsByUserId(userId)) {
            throw new BadRequestException("Profile already exists for user with id: " + userId);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Profile profile = profileMapper.toEntity(dto);
        profile.setUser(user);

        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toResponseDTO(savedProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileResponseDTO getProfileByUserId(Integer userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Profile not found for user with id: " + userId));
        return profileMapper.toResponseDTO(profile);
    }

    /**
     * Returns the profile enriched with the user's Role, so the profile page
     * can adapt its layout based on whether the viewer is a USER or AUTHOR.
     */
    @Override
    @Transactional(readOnly = true)
    public ProfileWithRoleDTO getProfileWithRoleByUserId(Integer userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Profile not found for user with id: " + userId));

        User user = profile.getUser();
        UserSummaryDTO userSummary = new UserSummaryDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );

        return new ProfileWithRoleDTO(
                profile.getId(),
                userSummary,
                user.getRole(),
                profile.getBiography(),
                profile.getBirthDate(),
                profile.getJoinDate()
        );
    }

    @Override
    public ProfileResponseDTO updateProfile(Integer userId, ProfileUpdateDTO dto) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Profile not found for user with id: " + userId));

        profileMapper.updateEntityFromDTO(dto, profile);
        Profile updatedProfile = profileRepository.save(profile);
        return profileMapper.toResponseDTO(updatedProfile);
    }

    @Override
    public void deleteProfile(Integer userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Profile not found for user with id: " + userId));

        profileRepository.delete(profile);
    }
}
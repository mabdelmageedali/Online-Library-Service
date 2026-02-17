package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.profile.ProfileRequestDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileResponseDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileUpdateDTO;
import com.onlineLibrary.Online.service.dto.profile.ProfileWithRoleDTO;

public interface ProfileService {

    ProfileResponseDTO createProfile(Integer userId, ProfileRequestDTO dto);

    ProfileResponseDTO getProfileByUserId(Integer userId);

    ProfileWithRoleDTO getProfileWithRoleByUserId(Integer userId);

    ProfileResponseDTO updateProfile(Integer userId, ProfileUpdateDTO dto);

    void deleteProfile(Integer userId);
}
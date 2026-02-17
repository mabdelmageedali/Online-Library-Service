package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.dto.registration.RegistrationRequestDTO;
import com.onlineLibrary.Online.service.dto.registration.RegistrationResponseDTO;
import com.onlineLibrary.Online.service.entity.Author;
import com.onlineLibrary.Online.service.entity.Profile;
import com.onlineLibrary.Online.service.entity.User;
import com.onlineLibrary.Online.service.enums.Role;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.repository.AuthorRepository;
import com.onlineLibrary.Online.service.repository.ProfileRepository;
import com.onlineLibrary.Online.service.repository.UserRepository;
import com.onlineLibrary.Online.service.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository    userRepository;
    private final AuthorRepository  authorRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder   passwordEncoder;

    @Override
    public RegistrationResponseDTO register(RegistrationRequestDTO dto) {

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new BadRequestException("Password and confirm password do not match");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Email already exists: " + dto.getEmail());
        }
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new BadRequestException("Phone number already exists: " + dto.getPhoneNumber());
        }

        Role effectiveRole = (dto.getRole() != null) ? dto.getRole() : Role.ROLE_USER;

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(effectiveRole);

        User savedUser = userRepository.save(user);

        Integer savedAuthorId = null;
        if (Role.ROLE_AUTHOR.equals(effectiveRole)) {
            String authorName = dto.getFirstName() + " " + dto.getLastName();

            // Guard against duplicate author name (respects existing validation)
            if (authorRepository.existsByAuthorName(authorName)) {
                throw new BadRequestException(
                        "An author with the name '" + authorName + "' already exists. " +
                                "Please use a different first/last name combination.");
            }

            Author author = new Author();
            author.setAuthorName(authorName);
            author.setBio(dto.getBiography() != null && !dto.getBiography().isBlank()
                    ? dto.getBiography()
                    : "Biography pending");
            author.setBirthDate(dto.getBirthDate());

            Author savedAuthor = authorRepository.save(author);
            savedAuthorId = savedAuthor.getId();
        }

        Profile profile = new Profile();
        profile.setUser(savedUser);
        profile.setBirthDate(dto.getBirthDate());
        if (dto.getBiography() != null && !dto.getBiography().isBlank()) {
            profile.setBiography(dto.getBiography());
        }

        Profile savedProfile = profileRepository.save(profile);

        return new RegistrationResponseDTO(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getPhoneNumber(),
                savedUser.getRole(),
                savedUser.getCreatedAt(),
                savedProfile.getId(),
                savedProfile.getBiography(),
                savedProfile.getBirthDate(),
                savedProfile.getJoinDate(),
                savedAuthorId
        );
    }
}

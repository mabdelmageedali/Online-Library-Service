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

/**
 * Shared registration logic for both USER and AUTHOR roles.
 *
 * Decision table:
 * ┌─────────────────┬───────────────────────────────────────────────────────┐
 * │ role            │ what gets persisted                                   │
 * ├─────────────────┼───────────────────────────────────────────────────────┤
 * │ ROLE_USER       │ users row  +  profile row                             │
 * │ ROLE_AUTHOR     │ users row  +  authors row  +  profile row             │
 * └─────────────────┴───────────────────────────────────────────────────────┘
 *
 * The Author entity has its own table (authors) with a separate PK.
 * Both are linked through the users row — the User carries the role flag
 * and the Profile always points at the User via user_id.
 *
 * No existing entities, validations, table/column names, repositories,
 * services, or mappers are modified.
 */
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

        // ── 1. Validate passwords ────────────────────────────────────────────
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new BadRequestException("Password and confirm password do not match");
        }

        // ── 2. Validate uniqueness ───────────────────────────────────────────
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Email already exists: " + dto.getEmail());
        }
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new BadRequestException("Phone number already exists: " + dto.getPhoneNumber());
        }

        // ── 3. Determine effective role (default to ROLE_USER) ───────────────
        Role effectiveRole = (dto.getRole() != null) ? dto.getRole() : Role.ROLE_USER;

        // ── 4. Build and persist the User row ────────────────────────────────
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(effectiveRole);

        User savedUser = userRepository.save(user);

        // ── 5. If AUTHOR → also persist an Author row ────────────────────────
        //
        //    The Author entity requires: authorName (NOT NULL), bio (NOT NULL),
        //    birthDate (NOT NULL).  We derive these from registration data:
        //      • authorName = firstName + " " + lastName
        //      • bio        = supplied biography (or empty string as placeholder)
        //      • birthDate  = supplied birthDate (same value used for Profile)
        //
        //    The author record is independent (no user_id FK exists on Author),
        //    consistent with the existing Author entity schema.
        //
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
            // bio is NOT NULL in the DB; use the supplied biography or a placeholder
            author.setBio(dto.getBiography() != null && !dto.getBiography().isBlank()
                    ? dto.getBiography()
                    : "Biography pending");
            author.setBirthDate(dto.getBirthDate());
            // deathDate is nullable — leave null

            Author savedAuthor = authorRepository.save(author);
            savedAuthorId = savedAuthor.getId();
        }

        // ── 6. Auto-create Profile linked to the User ────────────────────────
        Profile profile = new Profile();
        profile.setUser(savedUser);
        profile.setBirthDate(dto.getBirthDate());
        // biography is nullable in Profile — set it if provided
        if (dto.getBiography() != null && !dto.getBiography().isBlank()) {
            profile.setBiography(dto.getBiography());
        }
        // joinDate is handled by @CreationTimestamp — no manual assignment needed

        Profile savedProfile = profileRepository.save(profile);

        // ── 7. Build and return the unified response ─────────────────────────
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
                savedAuthorId          // null for ROLE_USER, populated for ROLE_AUTHOR
        );
    }
}

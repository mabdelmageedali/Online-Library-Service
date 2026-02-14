package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByUserId(Integer userId);

    Boolean existsByUserId(Integer userId);

}
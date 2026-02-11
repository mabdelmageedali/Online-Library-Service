package com.onlineLibrary.Online.Library.repository;

import com.onlineLibrary.Online.Library.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByUserId(Integer userId);

    Boolean existsByUserId(Integer userId);

}
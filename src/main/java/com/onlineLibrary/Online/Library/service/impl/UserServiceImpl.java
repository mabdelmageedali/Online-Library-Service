package com.onlineLibrary.Online.Library.service.impl;

import com.onlineLibrary.Online.Library.entity.User;
import com.onlineLibrary.Online.Library.repository.UserRepository;
import com.onlineLibrary.Online.Library.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }

    @Override
    public Optional<User> getUserById(Integer id) {

        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer id, User user) {

        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            oldUser.setPassword(user.getPassword());
        }

        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(Integer id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    public Boolean isEmailTaken(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean isPhoneTaken(String phoneNumber) {

        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}

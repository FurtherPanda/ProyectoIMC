package com.imc.service;

import com.imc.model.User;
import com.imc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {

        // Validar edad
        if (user.getAge() < 15) {
            throw new RuntimeException("User must be at least 15 years old");
        }

        // Validar estatura
        if (user.getHeight() < 1 || user.getHeight() > 2.5) {
            throw new RuntimeException("Height must be between 1m and 2.5m");
        }

        // Verificar si username ya existe
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        return userRepository.save(user);
    }

    public User login(String username, String password) {

        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

    }
}
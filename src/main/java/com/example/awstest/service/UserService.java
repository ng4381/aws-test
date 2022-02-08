package com.example.awstest.service;

import com.example.awstest.domain.User;
import com.example.awstest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
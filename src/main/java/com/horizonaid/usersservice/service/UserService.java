package com.horizonaid.usersservice.service;

import com.horizonaid.usersservice.model.User;
import com.horizonaid.usersservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        User savedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException(String.format("Cannot Find the User %s", user.getId())));

        savedUser.setName(user.getName());
        savedUser.setPassword(user.getPassword());
        savedUser.setEmail(user.getEmail());

        userRepository.save(user);
    }

    public User getUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent())
            return userOptional.get();
        else
            throw new RuntimeException("User Not Found!");
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}

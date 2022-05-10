package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.UserDTO;
import com.mopahta.projectmanager.exception.UserAlreadyExistsException;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDTO userDTO) throws UserAlreadyExistsException {
        if (userExists(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username " + userDTO.getUsername() + " is already taken");
        }
        userRepository.save(new User(userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()), "USER"));
    }

    public boolean userExists(String username) {
        return getUserByUsername(username) != null;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public List<User> getAdmins() {
        return userRepository.findAllByRolesContains("ADMIN");
    }

    public List<User> getUsers() {
        return userRepository.findAllByRolesContains("USER");
    }
}

package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.UserDTO;
import com.mopahta.projectmanager.exception.InvalidValuesException;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.exception.UserAlreadyExistsException;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDTO userDTO) throws UserAlreadyExistsException, InvalidValuesException {
        if (userDTO.getUsername().isEmpty()) {
            throw new InvalidValuesException("Username is empty");
        }
        else if (userDTO.getPassword().isEmpty()) {
            throw new InvalidValuesException("Password is empty");
        }
        if (userExists(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username " + userDTO.getUsername() + " is already taken");
        }
        userRepository.save(new User(userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                "USER")
        );
    }

    public String checkPasswordsMatch(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            return "Passwords don't match.";
        }
        return "";
    }

    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUserByUsername(String username) throws NotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new NotFoundException("No such user with username " + username);
        }
        return user.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("No such user with id " + id);
        }
        return user.get();
    }

    public List<User> getAdmins() {
        return userRepository.findAllByRolesContains("ADMIN");
    }

    public List<User> getUsers() {
        return userRepository.findAllByRolesContains("USER");
    }

    public void deactivateUserById(Long id) throws NotFoundException {
        User user = getUserById(id);
        user.setActive(false);
        userRepository.save(user);
    }

    public void activateUserById(Long id) throws NotFoundException {
        User user = getUserById(id);
        user.setActive(true);
        userRepository.save(user);
    }
}

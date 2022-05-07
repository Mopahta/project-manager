package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

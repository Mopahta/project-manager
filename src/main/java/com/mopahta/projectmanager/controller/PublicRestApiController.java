package com.mopahta.projectmanager.controller;

import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
public class PublicRestApiController {

    @Autowired
    UserService userService;

    @GetMapping("user/all")
    public List<User> getAllUserList() {
        return userService.getAllUsers();
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("user/all/admins")
    public List<User> getAdminsList() {
        return userService.getAdmins();
    }

    @GetMapping("user/all/users")
    public List<User> getUsersList() {
        return userService.getUsers();
    }
}

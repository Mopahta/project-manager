package com.mopahta.projectmanager.controller;

import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.service.ProjectService;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/private")
public class PublicRestApiController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @GetMapping("users/all")
    public List<User> getAllUsersList() {
        return userService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("users/all/admins")
    public List<User> getAdminsList() {
        return userService.getAdmins();
    }

    @GetMapping("users/all/users")
    public List<User> getUsersList() {
        return userService.getUsers();
    }

    @GetMapping("projects/all")
    public List<Project> getAllProjectsList() {
        return projectService.findAllProjects();
    }
}

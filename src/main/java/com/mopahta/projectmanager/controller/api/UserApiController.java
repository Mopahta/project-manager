package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.UserDTO;
import com.mopahta.projectmanager.dto.UserProjectDTO;
import com.mopahta.projectmanager.exception.UserAlreadyExistsException;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.service.UserProjectService;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/private/users")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProjectService userProjectService;

    @GetMapping("all")
    public List<User> getAllUsersList() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("all/admins")
    public List<User> getAdminsList() {
        return userService.getAdmins();
    }

    @GetMapping("all/users")
    public List<User> getUsersList() {
        return userService.getUsers();
    }

    @PostMapping(value = "add", consumes = "application/json")
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
        } catch (UserAlreadyExistsException e) {
            userDTO.setUsername("User with such name already exists!");
        }
        return userDTO;
    }

    @PostMapping(value = "add/all", consumes = "application/json")
    public List<UserDTO> addAllUsers(@RequestBody List<UserDTO> userDTO) {
        userDTO.forEach( (UserDTO user) -> {
            try {
                userService.registerUser(user);
            } catch (UserAlreadyExistsException e) {
                user.setUsername("User with such name already exists!");
            }
        });

        return userDTO;
    }

    @PutMapping(value = "add/project", consumes = "application/json")
    public UserProjectDTO addUserToProject(@RequestBody UserProjectDTO userProjectDTO) {
        userProjectService.addUserToProject(userProjectDTO);
        return userProjectDTO;
    }

    @DeleteMapping(value = "remove/project", consumes = "application/json")
    public UserProjectDTO removeUserFromProject(@RequestBody UserProjectDTO userProjectDTO) {
        userProjectService.removeUserFromProject(userProjectDTO);
        return userProjectDTO;
    }
}

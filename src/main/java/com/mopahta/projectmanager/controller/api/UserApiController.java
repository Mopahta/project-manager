package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.UserDTO;
import com.mopahta.projectmanager.dto.UserProjectDTO;
import com.mopahta.projectmanager.exception.InvalidValuesException;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.exception.UserAlreadyExistsException;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.model.UserProject;
import com.mopahta.projectmanager.service.UserProjectService;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/users")
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
    public User getUserById(@PathVariable Long id) throws NotFoundException {
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
    public UserDTO addUser(@RequestBody UserDTO userDTO) throws UserAlreadyExistsException, InvalidValuesException {
        userService.registerUser(userDTO);
        return userDTO;
    }

    @PutMapping(value = "add/project", consumes = "application/json")
    public UserProjectDTO addUserToProject(@RequestBody UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectService.addUserToProject(userProjectDTO);
        return userProjectDTO;
    }

    @PutMapping(value = "deactivate/{id}")
    public void deactivateUser(@PathVariable Long id) {

    }

    @PutMapping(value = "activate/{id}")
    public void activateUser(@PathVariable Long id) {

    }

    @DeleteMapping(value = "remove/project", consumes = "application/json")
    public UserProjectDTO removeUserFromProject(@RequestBody UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectService.removeUserFromProject(userProjectDTO);
        return userProjectDTO;
    }
}

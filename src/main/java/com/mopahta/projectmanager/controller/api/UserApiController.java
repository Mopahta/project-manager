package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.ApiAnswer;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProjectService userProjectService;

    @GetMapping("all")
    public List<UserDTO> getAllUsersList() {
        return userService.usersToDTO(userService.getAllUsers());
    }

    @GetMapping("all/active")
    public List<UserDTO> getActiveUsersList() {
        return userService.usersToDTO(userService.getActiveUsers());
    }

    @GetMapping("{id}")
    public UserDTO getUserById(@PathVariable Long id) throws NotFoundException {
        return userService.usersToDTO(List.of(userService.getUserById(id))).get(0);
    }

    @GetMapping("all/admins")
    public List<UserDTO> getAdminsList() {
        return userService.usersToDTO(userService.getAdmins());
    }

    @GetMapping("all/users")
    public List<UserDTO> getUsersList() {
        return userService.usersToDTO(userService.getUsers());
    }

    @PostMapping(value = "add", consumes = "application/json")
    public ApiAnswer addUser(@RequestBody UserDTO userDTO) throws UserAlreadyExistsException, InvalidValuesException {
        userService.registerUser(userDTO);
        return new ApiAnswer(HttpStatus.ACCEPTED, "User " + userDTO.getUsername() + " created");
    }

    @PutMapping(value = "add/project", consumes = "application/json")
    public ApiAnswer addUserToProject(@RequestBody UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectService.addUserToProject(userProjectDTO);
        return new ApiAnswer(HttpStatus.ACCEPTED,
                "Project " + userProjectDTO.getProjectId() +
                        " added to user " + userProjectDTO.getUserId());
    }

    @PutMapping(value = "deactivate/{id}")
    public ApiAnswer deactivateUser(@PathVariable Long id) throws NotFoundException {
        userService.deactivateUserById(id);
        return new ApiAnswer(HttpStatus.ACCEPTED, "User " + id + " deactivated");
    }

    @PutMapping(value = "activate/{id}")
    public ApiAnswer activateUser(@PathVariable Long id) throws NotFoundException {
        userService.activateUserById(id);
        return new ApiAnswer (HttpStatus.ACCEPTED, "User" + id + " activated");
    }

    @DeleteMapping(value = "remove/project", consumes = "application/json")
    public ApiAnswer removeUserFromProject(@RequestBody UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectService.removeUserFromProject(userProjectDTO);
        return new ApiAnswer(HttpStatus.OK,
                "Project " + userProjectDTO.getProjectId() +
                        " removed from user " + userProjectDTO.getUserId());
    }
}

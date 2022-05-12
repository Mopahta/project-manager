package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.ApiAnswer;
import com.mopahta.projectmanager.dto.ProjectDTO;
import com.mopahta.projectmanager.dto.UserProjectDTO;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.service.ProjectService;
import com.mopahta.projectmanager.service.UserProjectService;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectApiController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserProjectService userProjectService;

    @GetMapping("all")
    public List<ProjectDTO> getAllProjectsList() {
        return projectService.projectsToDTO(projectService.getAllProjects());
    }

    @GetMapping("{username}")
    public List<ProjectDTO> getUserProjectsList(@PathVariable String username) throws NotFoundException {
        return projectService.projectsToDTO(projectService.getUserProjectsByUsername(username));
    }

    @PutMapping(value = "add/user", consumes = "application/json")
    public ApiAnswer addUserToProject(@RequestBody UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectService.addUserToProject(userProjectDTO);
        return new ApiAnswer(HttpStatus.ACCEPTED,
                "User " + userProjectDTO.getUserId() +
                        " added to project " + userProjectDTO.getProjectId());
    }

    @DeleteMapping(value = "remove/user", consumes = "application/json")
    public ApiAnswer removeUserFromProject(@RequestBody UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectService.removeUserFromProject(userProjectDTO);
        return new ApiAnswer(HttpStatus.OK,
                "User " + userProjectDTO.getUserId() +
                        " removed from project " + userProjectDTO.getProjectId());
    }

    @PostMapping(value = "create", consumes = "application/json")
    public ApiAnswer createProject(@RequestBody ProjectDTO projectDTO) {
        projectService.createProject(projectDTO);
        return new ApiAnswer(HttpStatus.CREATED, "Project " + projectDTO.getName() + " created");
    }

    @DeleteMapping(value = "delete", consumes = "application/json")
    public ApiAnswer deleteProject(@RequestBody ProjectDTO projectDTO) {
        projectService.deleteProject(projectDTO);
        return new ApiAnswer(HttpStatus.OK, "Project " + projectDTO.getId() + " deleted");
    }
}

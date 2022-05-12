package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.ProjectDTO;
import com.mopahta.projectmanager.dto.UserProjectDTO;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.service.ProjectService;
import com.mopahta.projectmanager.service.UserProjectService;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Project> getAllProjectsList() {
        return projectService.getAllProjects();
    }

    @GetMapping("{username}")
    public List<ProjectDTO> getUserProjectsList(@PathVariable String username) throws NotFoundException {
        List<ProjectDTO> projects = new ArrayList<>();
        projectService.getUserProjectsByUsername(username).forEach(
                (Project project) -> {
                    projects.add(new ProjectDTO(
                            project.getId(),
                            project.getName(),
                            project.getCreation_date(),
                            project.getDescription(),
                            project.getProjectTasks()));
                });
        return projects;
    }

    @PutMapping(value = "add/user", consumes = "application/json")
    public UserProjectDTO addUserToProject(@RequestBody UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectService.addUserToProject(userProjectDTO);
        return userProjectDTO;
    }

    @DeleteMapping(value = "remove/user", consumes = "application/json")
    public UserProjectDTO removeUserFromProject(@RequestBody UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectService.removeUserFromProject(userProjectDTO);
        return userProjectDTO;
    }

    @PostMapping(value = "create", consumes = "application/json")
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
        projectService.createProject(projectDTO);
        return projectDTO;
    }

    @DeleteMapping(value = "delete", consumes = "application/json")
    public ProjectDTO deleteProject(@RequestBody ProjectDTO projectDTO) {
        projectService.deleteProject(projectDTO);
        return projectDTO;
    }
}

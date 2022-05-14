package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.*;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.service.ProjectService;
import com.mopahta.projectmanager.service.UserProjectService;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectApiController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    UserProjectService userProjectService;

    @GetMapping("all")
    public List<ProjectDTO> getAllProjectsList() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (userService.hasAdminAuthority(authentication.getAuthorities())) {
                return projectService.projectsToDTO(projectService.getAllProjects());
            }
        }
        return projectService.projectsToDTO(
                projectService.findUserProjects(
                        authentication.getAuthorities(), authentication.getName()
                ));
    }

    @GetMapping("{username}")
    public List<ProjectDTO> getUserProjectsList(@PathVariable String username) throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (userService.hasAdminAuthority(authentication.getAuthorities())) {
                return projectService.projectsToDTO(projectService.getUserProjectsByUsername(username));
            }
        }
        return Collections.singletonList(new ProjectDTO(0L, "Not enough authorities to get projects", new Date(), ""));
    }

    @GetMapping("users/{projectId}")
    public List<UserInProjectDTO> getUsersInProjectList(@PathVariable Long projectId) throws NotFoundException {
        return userProjectService.getUsersInProjectById(projectId);
    }

    @GetMapping("page")
    public Page<ProjectDTO> getProjectPage (@RequestParam("page") Optional<Integer> page,
                                            @RequestParam("size") Optional<Integer> size,
                                            @RequestParam("sortedBy") Optional<String> sortedBy,
                                            @RequestParam("filterByName") Optional<String> name,
                                            @RequestParam("filterByDecription") Optional<String> description) throws NotFoundException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String sorted = sortedBy.orElse("name");
        String filterByName = name.orElse("");
        String filterByDescription = description.orElse("");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (userService.hasAdminAuthority(authentication.getAuthorities())) {
                return projectService.findProjectsPaginated("", PageRequest.of(currentPage - 1, pageSize),
                        sorted, filterByName, filterByDescription);
            }
        }

        return projectService.findProjectsPaginated(authentication.getName(), PageRequest.of(currentPage - 1, pageSize),
                sorted, filterByName, filterByDescription);
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
    public ApiAnswer deleteProject(@RequestBody ProjectDTO projectDTO) throws NotFoundException {
        projectService.deleteProject(projectDTO);
        return new ApiAnswer(HttpStatus.OK, "Project " + projectDTO.getId() + " deleted");
    }

    @PutMapping(value = "update/{projectId}", consumes = "application/json")
    public ApiAnswer updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long projectId) throws NotFoundException {
        projectService.updateProject(projectDTO, projectId);
        return new ApiAnswer(HttpStatus.OK, "Project " + projectId + " updated");
    }
}

package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.ProjectDTO;
import com.mopahta.projectmanager.dto.UserProjectDTO;
import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.model.UserProject;
import com.mopahta.projectmanager.repository.ProjectRepository;
import com.mopahta.projectmanager.repository.UserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserProjectRepository userProjectRepository;

    @Autowired
    UserService userService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void createProject(ProjectDTO projectDTO) {
        projectRepository.save(new Project(projectDTO.getName(), projectDTO.getDescription()));
    }

    public void deleteProject(ProjectDTO projectDTO) {
        projectRepository.deleteById(projectDTO.getId());
    }

    public List<Project> findUserProjects(Collection<? extends GrantedAuthority> authorities, String username) {
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return getAllProjects();
            }
        }

        return getUserProjectsByUsername(username);
    }

    public List<Project> getUserProjectsByUsername(String username) {
        List<Project> projects = new ArrayList<>();

        userProjectRepository.findAllByUser(userService.getUserByUsername(username)).
                forEach((UserProject userProject) -> {
                    projects.add(userProject.getProject());
                });

        return projects;
    }

    public Project getProjectById(Long id) {
        return projectRepository.getById(id);
    }
}

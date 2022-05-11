package com.mopahta.projectmanager.service;

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

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> findUserProjects(Collection<? extends GrantedAuthority> authorities, String username) {
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return findAllProjects();
            }
        }
        List<Project> projects = new ArrayList<>();

        userProjectRepository.findAllByUser(userService.getUserByUsername(username)).
                forEach((UserProject userProject) -> {
                    projects.add(userProject.getProject());
                } );

        return projects;
    }
}

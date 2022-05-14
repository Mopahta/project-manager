package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.ProjectDTO;
import com.mopahta.projectmanager.dto.UserInProjectDTO;
import com.mopahta.projectmanager.dto.UserProjectDTO;
import com.mopahta.projectmanager.exception.NotFoundException;
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
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserProjectRepository userProjectRepository;

    @Autowired
    UserService userService;

    public List<ProjectDTO> projectsToDTO(List<Project> projects) {
        List<ProjectDTO> projectsDTO = new ArrayList<>();
        projects.forEach(
                (Project project) -> {
                    projectsDTO.add(new ProjectDTO(
                            project.getId(),
                            project.getName(),
                            project.getCreation_date(),
                            project.getDescription()));
                });
        return projectsDTO;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void createProject(ProjectDTO projectDTO) {
        projectRepository.save(new Project(projectDTO.getName(), projectDTO.getDescription()));
    }

    public void deleteProject(ProjectDTO projectDTO) throws NotFoundException {
        projectRepository.delete(getProjectById(projectDTO.getId()));
    }

    public void updateProject(ProjectDTO projectDTO, Long projectId) throws NotFoundException {
        Project project = getProjectById(projectId);
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        projectRepository.save(project);
    }

    public List<Project> findUserProjects(Collection<? extends GrantedAuthority> authorities, String username) throws NotFoundException {
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return getAllProjects();
            }
        }

        return getUserProjectsByUsername(username);
    }

    public List<Project> getUserProjectsByUsername(String username) throws NotFoundException {
        List<Project> projects = new ArrayList<>();

        userProjectRepository.findAllByUser(userService.getUserByUsername(username)).
                forEach((UserProject userProject) -> {
                    projects.add(userProject.getProject());
                });

        return projects;
    }

    public Project getProjectById(Long id) throws NotFoundException{
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("No such project with id " + id);
        }
        return project.get();
    }
}

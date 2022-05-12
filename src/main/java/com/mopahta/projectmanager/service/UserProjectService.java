package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.UserProjectDTO;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.model.UserProject;
import com.mopahta.projectmanager.repository.UserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProjectService {

    @Autowired
    UserProjectRepository userProjectRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    private UserProject DTOToUserProject(UserProjectDTO userProjectDTO) throws NotFoundException {
        UserProject userProject = new UserProject();

        userProject.setUser(userService.getUserById(userProjectDTO.getUserId()));
        userProject.setProject(projectService.getProjectById(userProjectDTO.getProjectId()));
        userProject.setRoles(userProjectDTO.getRole());

        return userProject;
    }

    public void addUserToProject(UserProjectDTO userProjectDTO) throws NotFoundException {
        userProjectRepository.save(DTOToUserProject(userProjectDTO));
    }

    public void removeUserFromProject(UserProjectDTO userProjectDTO) throws NotFoundException{
        userProjectRepository.deleteByUserAndProject(
                userService.getUserById(userProjectDTO.getUserId()),
                projectService.getProjectById(userProjectDTO.getProjectId()));
    }
}

package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.UserInProjectDTO;
import com.mopahta.projectmanager.dto.UserProjectDTO;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.model.UserProject;
import com.mopahta.projectmanager.repository.UserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public List<UserInProjectDTO> getUsersInProjectById(Long id) throws NotFoundException {
        List<UserProject> userProjects = userProjectRepository.findAllByProject(projectService.getProjectById(id));

        List<UserInProjectDTO> usersInProjectDTO = new ArrayList<>();
        for (UserProject userProject : userProjects) {
            Long userId = userProject.getId().getUserId();
            usersInProjectDTO.add(new UserInProjectDTO(
                    userId,
                    userService.getUserById(userId).getUsername(),
                    userProject.getId().getProjectId(),
                    userProject.getRoles()
            ));
        }

        return usersInProjectDTO;
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

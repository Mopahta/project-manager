package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.ProjectTaskDTO;
import com.mopahta.projectmanager.model.ProjectTask;
import com.mopahta.projectmanager.model.ProjectTaskKey;
import com.mopahta.projectmanager.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    ProjectTaskRepository projectTaskRepository;

    @Autowired
    ProjectService projectService;

    private ProjectTask DTOToProjectTask(ProjectTaskDTO projectTaskDTO) {
        ProjectTask task = new ProjectTask();

        task.setProject(projectService.getProjectById(projectTaskDTO.getProjectId()));
        task.setTask(projectTaskDTO.getTask());
        task.setFinished(false);
        task.setId(new ProjectTaskKey(projectTaskDTO.getOrderId(), projectTaskDTO.getProjectId()));

        return task;
    }

    public List<ProjectTask> getTasksByProjectId(Long projectId) {
        return projectTaskRepository.findAllByProject(projectService.getProjectById(projectId));
    }

    public void addTaskToProject(ProjectTaskDTO projectTaskDTO) {
        projectTaskRepository.save(DTOToProjectTask(projectTaskDTO));
    }
}

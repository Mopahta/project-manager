package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.ProjectTaskDTO;
import com.mopahta.projectmanager.exception.InvalidValuesException;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.model.ProjectTask;
import com.mopahta.projectmanager.model.ProjectTaskKey;
import com.mopahta.projectmanager.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    ProjectTaskRepository projectTaskRepository;

    @Autowired
    ProjectService projectService;

    public List<ProjectTaskDTO> projectTasksToDTO(List<ProjectTask> projectTasks) {
        List<ProjectTaskDTO> projectTasksDTO = new ArrayList<>();

        projectTasks.forEach(
                (ProjectTask projectTask) -> {
                    projectTasksDTO.add(new ProjectTaskDTO(
                            projectTask.getId().getId(),
                            projectTask.getId().getProjectId(),
                            projectTask.getTask(),
                            projectTask.isFinished()));
        });
        return projectTasksDTO;
    }

    private ProjectTask DTOToProjectTask(ProjectTaskDTO projectTaskDTO) throws NotFoundException {
        ProjectTask task = new ProjectTask();

        task.setProject(projectService.getProjectById(projectTaskDTO.getProjectId()));
        task.setTask(projectTaskDTO.getTask());
        task.setFinished(false);
        task.setId(new ProjectTaskKey(projectTaskDTO.getOrderId(), projectTaskDTO.getProjectId()));

        return task;
    }

    public List<ProjectTask> getTasksByProjectId(Long projectId) throws NotFoundException {
        return projectTaskRepository.findAllByProject(projectService.getProjectById(projectId));
    }

    public void addTaskToProject(ProjectTaskDTO projectTaskDTO) throws NotFoundException{
        projectTaskRepository.save(DTOToProjectTask(projectTaskDTO));
    }

    public void removeTaskFromProject(ProjectTaskDTO projectTaskDTO) throws InvalidValuesException {
        if (!projectTaskRepository.existsById(new ProjectTaskKey(
                projectTaskDTO.getOrderId(),
                projectTaskDTO.getProjectId()
        ))) {
            throw new InvalidValuesException(
                    "No task matched to project " + projectTaskDTO.getProjectId()
                            + " order id " + projectTaskDTO.getOrderId());
        }
        projectTaskRepository.deleteById(
                new ProjectTaskKey(projectTaskDTO.getOrderId(), projectTaskDTO.getProjectId())
        );
    }
}

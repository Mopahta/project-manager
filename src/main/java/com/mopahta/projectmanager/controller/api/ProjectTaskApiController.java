
package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.ApiAnswer;
import com.mopahta.projectmanager.dto.ProjectTaskDTO;
import com.mopahta.projectmanager.exception.InvalidValuesException;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.model.ProjectTask;
import com.mopahta.projectmanager.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects/tasks")
public class ProjectTaskApiController {

    @Autowired
    ProjectTaskService projectTaskService;

    @GetMapping("{projectId}")
    public List<ProjectTaskDTO> getTasksFromProject(@PathVariable Long projectId) throws NotFoundException {
        return projectTaskService.projectTasksToDTO(projectTaskService.getTasksByProjectId(projectId));
    }

    @PutMapping("")
    public ApiAnswer addTaskToProject(@RequestBody ProjectTaskDTO projectTaskDTO) throws NotFoundException {
        projectTaskService.addTaskToProject(projectTaskDTO);
        return new ApiAnswer(HttpStatus.ACCEPTED,
                "Task " + projectTaskDTO.getTask() + " added to project " + projectTaskDTO.getProjectId());
    }

    @DeleteMapping("")
    public ApiAnswer removeTaskFromProject(@RequestBody ProjectTaskDTO projectTaskDTO) throws InvalidValuesException {
        projectTaskService.removeTaskFromProject(projectTaskDTO);
        return new ApiAnswer(HttpStatus.OK,
                "Task "+ projectTaskDTO.getOrderId() + " removed from project " + projectTaskDTO.getProjectId());
    }
}

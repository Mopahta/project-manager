
package com.mopahta.projectmanager.controller.api;

import com.mopahta.projectmanager.dto.ProjectTaskDTO;
import com.mopahta.projectmanager.model.ProjectTask;
import com.mopahta.projectmanager.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/private/projects/tasks")
public class ProjectTaskApiController {

    @Autowired
    ProjectTaskService projectTaskService;

    @GetMapping("{projectId}")
    public List<ProjectTask> getTasksFromProject(@PathVariable Long projectId) {
        return projectTaskService.getTasksByProjectId(projectId);
    }

    @PutMapping("")
    public ProjectTaskDTO addTaskToProject(@RequestBody ProjectTaskDTO projectTaskDTO) {
        projectTaskService.addTaskToProject(projectTaskDTO);
        return projectTaskDTO;
    }

}

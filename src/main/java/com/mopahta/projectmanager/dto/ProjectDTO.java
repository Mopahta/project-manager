package com.mopahta.projectmanager.dto;

import com.mopahta.projectmanager.model.ProjectTask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private Date creation_date;

    private String description;

    List<ProjectTask> projectTasks;
}

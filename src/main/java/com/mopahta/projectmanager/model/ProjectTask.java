package com.mopahta.projectmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "project_task", schema = "project_manager_db")
@Getter @Setter @NoArgsConstructor
public class ProjectTask {

    @EmbeddedId
    private ProjectTaskKey id = new ProjectTaskKey();

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    private String task;

    private boolean finished;
}

package com.mopahta.projectmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "project_task", schema = "project_manager_db")
@SQLDelete(sql = "UPDATE project_task SET deleted = true WHERE order_id = ? and project_id = ?")
@Where(clause = "deleted=false")
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

    private boolean deleted = Boolean.FALSE;
}

package com.mopahta.projectmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects", schema = "project_manager_db")
@Getter @Setter @NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description = "";

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation_date = new Date();

    @OneToMany(mappedBy = "project")
    List<UserProject> userProjects;

    @OneToMany(mappedBy = "project")
    List<ProjectTask> projectTasks;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

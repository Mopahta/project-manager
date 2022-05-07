package com.mopahta.projectmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "project")
    List<UserProject> userProjects;
}

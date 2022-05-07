package com.mopahta.projectmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "user_project", schema = "project_manager_db")
public class UserProject {

    @EmbeddedId
    private UserProjectKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    private String roles;
}

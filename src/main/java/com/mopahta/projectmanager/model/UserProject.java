package com.mopahta.projectmanager.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

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

    @CreatedDate
    private Date date_joined;
}

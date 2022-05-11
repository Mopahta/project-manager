package com.mopahta.projectmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_project", schema = "project_manager_db")
@Getter @Setter
public class UserProject {

    @EmbeddedId
    private UserProjectKey id = new UserProjectKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    private String roles;

    @CreationTimestamp
    private Date date_joined;
}

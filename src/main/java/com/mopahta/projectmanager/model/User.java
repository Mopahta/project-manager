package com.mopahta.projectmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users", schema = "project_manager_db")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean active;

    private String roles = "";

    @OneToMany(mappedBy = "user")
    List<UserProject> userProjects;

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = true;
    }

    public List<String> getRolesList() {
        if (roles.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(roles.split(","));
    }
}

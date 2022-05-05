package com.mopahta.projectmanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "project-manager-db")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;

}

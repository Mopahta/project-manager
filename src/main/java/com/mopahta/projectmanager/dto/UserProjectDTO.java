package com.mopahta.projectmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserProjectDTO {

    private Long projectId;

    private Long userId;

    private String role;

    private Date joindate;
}

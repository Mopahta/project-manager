package com.mopahta.projectmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserInProjectDTO {

    private Long userId;

    private String name;

    private Long projectId;

    private String rolesInProject;
}

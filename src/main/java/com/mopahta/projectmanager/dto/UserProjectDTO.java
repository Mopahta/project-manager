package com.mopahta.projectmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserProjectDTO {

    private Long projectId;

    private Long userId;

    private String role;
}

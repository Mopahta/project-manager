package com.mopahta.projectmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProjectTaskDTO {

    private Long orderId;

    private Long projectId;

    private String task;

    private boolean finished;
}

package com.mopahta.projectmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProjectTaskDTO {

    private Long orderId;

    private Long projectId;

    private String task;
}

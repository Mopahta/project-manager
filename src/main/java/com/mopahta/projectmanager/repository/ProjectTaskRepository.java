package com.mopahta.projectmanager.repository;

import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.model.ProjectTask;
import com.mopahta.projectmanager.model.ProjectTaskKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, ProjectTaskKey> {
    List<ProjectTask> findAllByProject(Project project);
}

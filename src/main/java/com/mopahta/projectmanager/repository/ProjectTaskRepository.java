package com.mopahta.projectmanager.repository;

import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.model.ProjectTask;
import com.mopahta.projectmanager.model.ProjectTaskKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, ProjectTaskKey> {
    List<ProjectTask> findAllByProject(Project project);
}

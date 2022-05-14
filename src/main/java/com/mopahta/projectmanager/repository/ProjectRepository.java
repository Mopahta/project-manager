package com.mopahta.projectmanager.repository;

import com.mopahta.projectmanager.model.Project;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {
    @NonNull
    List<Project> findAll();
    List<Project> findAllByNameContainsAndDescriptionContains(String name, String Description, Sort sort);
    List<Project> findAllByIdInAndNameContainsAndDescriptionContains(
            List<Long> ids, String name, String description, Sort sort);
}

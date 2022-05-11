package com.mopahta.projectmanager.repository;

import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.model.UserProject;
import com.mopahta.projectmanager.model.UserProjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectKey> {
    List<UserProject> findAllByUser(User user);
    List<UserProject> findAllByProject(Project project);
    @Transactional
    void deleteByUserAndProject(User user, Project project);
}

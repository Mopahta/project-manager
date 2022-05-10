package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.model.Project;
import com.mopahta.projectmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}

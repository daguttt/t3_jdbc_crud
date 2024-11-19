package org.example.controllers;

import org.example.entities.Project;
import org.example.models.ProjectsModel;

import java.util.List;

public class ProjectsController {
    private final ProjectsModel projectsModel;

    public ProjectsController(ProjectsModel projectsModel) {
        this.projectsModel = projectsModel;
    }

    public Project create(Project baseProject) {
        return this.projectsModel.create(baseProject);
    }

    public List<Project> findAll() {
        return this.projectsModel.findAll();
    }
}

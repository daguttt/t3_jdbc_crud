package org.example.controllers;

import org.example.entities.Project;
import org.example.models.ProjectsModel;

public class ProjectsController {
    private final ProjectsModel projectsModel;

    public ProjectsController(ProjectsModel projectsModel) {
        this.projectsModel = projectsModel;
    }

    public Project create(Project baseProject) {
        return this.projectsModel.create(baseProject);
    }
}

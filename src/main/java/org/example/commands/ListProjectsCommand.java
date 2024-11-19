package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.commands.interfaces.MenuCommand;
import org.example.controllers.ProjectsController;
import org.example.entities.Project;

import javax.swing.*;
import java.util.List;

public class ListProjectsCommand extends AuthenticatedCommand {
    private final ProjectsController projectsController;

    public ListProjectsCommand(AppState appState, ProjectsController projectsController) {
        super(appState);
        this.projectsController = projectsController;
    }


    @Override
    public void execute(Menu menu) {
        this.executeAuthenticated(menu, (it) -> {
            List<Project> projectList = this.projectsController.findAll();

            if (projectList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay proyectos creados aun.");
                return;
            }

            List<String> projectsAsStringList = projectList.stream().map(Project::toString).toList();
            JOptionPane.showMessageDialog(null, String.join("\n--------------\n", projectsAsStringList));
        });
    }
}

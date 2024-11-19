package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.controllers.InscriptionsController;
import org.example.controllers.ProjectsController;
import org.example.entities.Project;
import org.example.entities.User;
import org.example.utils.InputRequester;

import javax.swing.*;
import java.util.List;

public class ListProjectVolunteersCommand extends AuthenticatedCommand {
    private final ProjectsController projectsController;
    private final InscriptionsController inscriptionsController;

    public ListProjectVolunteersCommand(AppState appState, ProjectsController projectsController, InscriptionsController inscriptionsController) {
        super(appState);
        this.projectsController = projectsController;
        this.inscriptionsController = inscriptionsController;
    }

    @Override
    public void execute(Menu menu) {
        this.executeAuthenticated(menu, (it) -> {
            // Request a project to list its volunteers
            List<Project> projectList = this.projectsController.findAll();

            if (projectList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay proyectos creados aun.");
                return;
            }

            List<String> projectsAsStringList = projectList.stream().map(Project::getTitle).toList();
            int projectIndex = InputRequester.requestAnIndexFrom(projectsAsStringList, "Selecciona un proyecto para ver sus voluntarios");
            Project selectedProject = projectList.get(projectIndex);

            // Show volunteers of the project
            List<User> volunteers = this.inscriptionsController.findVolunteers(selectedProject);

            if (volunteers.isEmpty()) {
                JOptionPane.showMessageDialog(null, String.format("No hay voluntarios inscritos en el proyecto:%n'%s'", selectedProject.getTitle()));
                return;
            }

            List<String> volunteersAsStringList = volunteers.stream()
                    .map((user -> String.format("""
                            Nombre: %s
                            Correo: %s
                            """, user.getName(), user.getEmail()))).toList();

            String message = String.format("""
                    Voluntarios del proyecto: %s%n
                    
                    %s""", selectedProject.getTitle(), String.join("\n--------------\n", volunteersAsStringList));
            JOptionPane.showMessageDialog(null, message);

        });
    }
}

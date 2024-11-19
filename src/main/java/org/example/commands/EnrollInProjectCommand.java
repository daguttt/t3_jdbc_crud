package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.controllers.InscriptionsController;
import org.example.controllers.ProjectsController;
import org.example.entities.Inscription;
import org.example.entities.Project;
import org.example.enums.Roles;
import org.example.utils.InputRequester;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EnrollInProjectCommand extends AuthenticatedCommand {
    private final ProjectsController projectsController;
    private final InscriptionsController inscriptionsController;

    public EnrollInProjectCommand(AppState appState, ProjectsController projectsController, InscriptionsController inscriptionsController) {
        super(appState);
        this.projectsController = projectsController;
        this.inscriptionsController = inscriptionsController;
    }

    @Override
    public void execute(Menu menu) {
        executeAuthenticated(menu, (it) -> {
            if (appState.getUser().getRole() == Roles.PUBLISHER) {
                JOptionPane.showMessageDialog(null, "Solo los voluntarios pueden inscribirte en proyectos.");
               return;
            }
            List<Project> projectList = this.projectsController.findAll();

            if (projectList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay proyectos para inscribirte en por el momento.");
                return;
            }

            List<String> projectsAsStringList = projectList.stream().map(Project::getTitle).toList();
            int projectIndex = InputRequester.requestAnIndexFrom(projectsAsStringList, "Selecciona un proyecto para inscribirte");

            Project selectedProject = projectList.get(projectIndex);
            var baseInscription = new Inscription(appState.getUser(), selectedProject, Date.valueOf(LocalDate.now()));
            var createdInscription = this.inscriptionsController.create(baseInscription);

            if (createdInscription == null) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error. Puede que ya estés inscrito en el proyecto.");
                return;
            }

            JOptionPane.showMessageDialog(null, "Te has inscrito en el proyecto.");

        });
    }
}

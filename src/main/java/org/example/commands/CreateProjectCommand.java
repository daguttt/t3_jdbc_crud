package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.commands.interfaces.MenuCommand;
import org.example.controllers.ProjectsController;
import org.example.entities.Project;
import org.example.enums.Roles;
import org.example.utils.InputRequester;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;

public class CreateProjectCommand implements MenuCommand {
    private final AppState appState;
    private final ProjectsController projectsController;

    public CreateProjectCommand(AppState appState, ProjectsController projectsController) {
        this.appState = appState;
        this.projectsController = projectsController;
    }

    @Override
    public void execute(Menu menu) {
        if (appState.getUser().getRole() == Roles.VOLUNTEER) {
            JOptionPane.showMessageDialog(null, "No tienes permiso para crear proyectos.");
            return;
        }

        String title = InputRequester.requestString("Ingresa el titulo del proyecto");
        String description = InputRequester.requestString("Ingresa la descripción del proyecto");
        LocalDate startDate = InputRequester.requestLocalDate("Ingresa la fecha de inicio del proyecto (En el futuro o hoy)").orElseThrow();

        if (startDate.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "La fecha de inicio del proyecto debe ser en el futuro o hoy.");
            return;
        }

        LocalDate endDate = InputRequester.requestLocalDate(String.format("Ingresa la fecha de fin del proyecto (Después de la fecha de inicio '%TD')", startDate)).orElseThrow();

        if (endDate.isBefore(startDate)) {
            JOptionPane.showMessageDialog(null, "La fecha de fin del proyecto debe ser posterior a la fecha de inicio.");
            return;
        }

        Project baseProject = new Project(
                title,
                description,
                Date.valueOf(startDate),
                Date.valueOf(endDate),
                appState.getUser().getId()
        );
        Project createdProject = this.projectsController.create(baseProject);
        JOptionPane.showMessageDialog(null, String.format("¡Proyecto '%s' creado correctamente!", createdProject.getTitle()));

    }
}

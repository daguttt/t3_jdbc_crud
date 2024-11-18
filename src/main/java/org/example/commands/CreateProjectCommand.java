package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.commands.interfaces.MenuCommand;
import org.example.controllers.ProjectsController;
import org.example.entities.Project;
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
        String title = InputRequester.requestString("Ingresa el titulo del proyecto");
        String description = InputRequester.requestString("Ingresa la descripción del proyecto");
        LocalDate startDate = InputRequester.requestLocalDate("Ingresa la fecha de inicio del proyecto").orElseThrow();
        LocalDate endDate = InputRequester.requestLocalDate("Ingresa la fecha de fin del proyecto").orElseThrow();

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

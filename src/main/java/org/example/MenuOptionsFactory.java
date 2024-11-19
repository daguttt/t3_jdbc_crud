package org.example;

import org.example.commands.*;

import org.example.controllers.ProjectsController;
import org.example.controllers.UsersController;

import java.util.List;

public class MenuOptionsFactory {
    private final AppState appState;

    private final UsersController usersController;
    private final ProjectsController projectsController;

    public MenuOptionsFactory(AppState appState, UsersController usersController, ProjectsController projectsController) {
        this.appState = appState;
        this.usersController = usersController;
        this.projectsController = projectsController;
    }

    public List<MenuOption> getAuthMenuCommands() {
        return List.of(
                new MenuOption("Iniciar sesión.", new LoginCommand(this.appState, this.usersController)),
                new MenuOption("Registrarse", new RegisterCommand(this.appState, this.usersController)),
                new MenuOption("Salir", new CloseAllCommand())
        );
    }

    public List<MenuOption> getMainMenuCommands() {
        return List.of(
                new MenuOption("Crear proyecto", new CreateProjectCommand(this.appState, this.projectsController)),
                new MenuOption("Listar proyectos", new ListProjectsCommand(this.appState,this.projectsController)),
                new MenuOption("Cerrar sesión", new LogoutCommand(this.appState)),
                new MenuOption("Salir", new CloseAllCommand())
        );
    }
}

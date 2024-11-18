package org.example;

import org.example.commands.LoginCommand;
import org.example.commands.LogoutCommand;
import org.example.commands.RegisterCommand;
import org.example.commands.interfaces.MenuCommand;
import org.example.controllers.UsersController;

import java.util.List;

public class MenuOptionsFactory {

    private final AppState appState;
    private final UsersController usersController;

    public MenuOptionsFactory(AppState appState, UsersController usersController) {
        this.appState = appState;
        this.usersController = usersController;
    }

    public List<MenuOption> getAuthMenuCommands() {
        return List.of(
                new MenuOption("Iniciar sesión.", new LoginCommand(appState, this.usersController)),
                new MenuOption("Registrarse", new RegisterCommand(appState, this.usersController))
        );
    }

    public List<MenuOption> getMainMenuCommands() {
        return List.of(
                new MenuOption("Cerrar sesión", new LogoutCommand(appState))
        );
    }
}

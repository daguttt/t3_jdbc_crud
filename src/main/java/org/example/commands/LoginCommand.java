package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.commands.interfaces.MenuCommand;
import org.example.controllers.UsersController;
import org.example.entities.User;
import org.example.utils.InputRequester;

import javax.swing.*;

public class LoginCommand implements MenuCommand {
    private final AppState appState;
    private final UsersController usersController;

    public LoginCommand(AppState appState, UsersController usersController) {
        this.appState = appState;
        this.usersController = usersController;
    }

    @Override
    public void execute(Menu menu) {
        String email = InputRequester.requestString("Ingresa tu correo electrónico");
        String password = InputRequester.requestString("Ingresa tu contraseña");

        User user = new User(email, password);
        User loggedInUser = this.usersController.login(user);

        menu.setCanOpenSubMenu(loggedInUser != null);

        if (loggedInUser == null) {
            JOptionPane.showMessageDialog(null, "El correo, la contraseña o ambos son incorrectos.");
            return;
        }

        appState.setUser(loggedInUser);
        JOptionPane.showMessageDialog(null, "¡Has iniciado sesión correctamente!");
    }
}

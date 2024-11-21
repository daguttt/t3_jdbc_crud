package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.commands.interfaces.MenuCommand;

import javax.swing.*;

public abstract class AuthenticatedCommand implements MenuCommand {
    protected final AppState appState;

    protected AuthenticatedCommand(AppState appState) {
        this.appState = appState;
    }

    public boolean isAuth() {
        return appState.getUser() != null;
    }

    public void executeAuthenticated(Menu menu, MenuCommand command) {
        if (!isAuth()) {
            JOptionPane.showMessageDialog(null, "Debes estar logueado para realizar esta acción.");
            return;
        }
        command.execute(menu);
    }
}

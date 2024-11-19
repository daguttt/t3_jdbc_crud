package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.commands.interfaces.MenuCommand;

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
            menu.reOpenParentMenu();
            return;
        }
        command.execute(menu);
    }
}

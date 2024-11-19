package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.commands.interfaces.MenuCommand;

public class LogoutCommand implements MenuCommand {
    private final AppState appState;

    public LogoutCommand(AppState appState) {
        this.appState = appState;
    }

    @Override
    public void execute(Menu menu) {
        appState.setUser(null);
        menu.close();
    }
}

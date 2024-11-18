package org.example.commands;

import org.example.AppState;
import org.example.commands.interfaces.MenuCommand;

public class LogoutCommand implements MenuCommand {
    private final AppState appState;

    public LogoutCommand(AppState appState) {
        this.appState = appState;
    }

    @Override
    public void execute() {
        appState.setUser(null);
    }

}

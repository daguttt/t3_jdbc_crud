package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.commands.interfaces.MenuCommand;

import javax.swing.*;

public class CloseAllCommand implements MenuCommand {
    private final AppState appState;

    public CloseAllCommand(AppState appState) {
        this.appState = appState;
    }


    @Override
    public void execute(Menu menu) {
        appState.setStatus(AppState.Status.CLOSED);
        menu.close();
        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
    }
}

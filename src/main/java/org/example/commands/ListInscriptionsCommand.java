package org.example.commands;

import org.example.AppState;
import org.example.Menu;
import org.example.controllers.InscriptionsController;
import org.example.entities.Inscription;
import org.example.enums.Roles;

import javax.swing.*;
import java.util.List;

public class ListInscriptionsCommand extends AuthenticatedCommand {
    private final InscriptionsController inscriptionsController;

    public ListInscriptionsCommand(AppState appState, InscriptionsController inscriptionsController) {
        super(appState);
        this.inscriptionsController = inscriptionsController;
    }

    @Override
    public void execute(Menu menu) {
        executeAuthenticated(menu, (it) -> {

            if (appState.getUser().getRole() == Roles.PUBLISHER) {
                JOptionPane.showMessageDialog(null, "Solo los voluntarios tienen inscripciones a proyectos.");
                return;
            }

            List<Inscription> inscriptions = this.inscriptionsController.findAll(this.appState.getUser());
            if (inscriptions.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No te has inscrito a un proyecto aun.");
                return;
            }
            List<String> inscriptionsAsStringList = inscriptions.stream().map(Inscription::toString).toList();
            JOptionPane.showMessageDialog(null, String.join("\n--------------\n", inscriptionsAsStringList));
        });
    }
}

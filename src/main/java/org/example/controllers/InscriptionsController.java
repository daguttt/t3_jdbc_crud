package org.example.controllers;

import org.example.entities.Inscription;
import org.example.models.InscriptionsModel;

public class InscriptionsController {
    private final InscriptionsModel inscriptionsModel;

    public InscriptionsController(InscriptionsModel inscriptionsModel) {
        this.inscriptionsModel = inscriptionsModel;
    }

    public Inscription create(Inscription baseInscription) {
        return this.inscriptionsModel.create(baseInscription);
    }
}

package org.example.controllers;

import org.example.entities.Inscription;
import org.example.entities.User;
import org.example.models.InscriptionsModel;

import java.util.List;

public class InscriptionsController {
    private final InscriptionsModel inscriptionsModel;

    public InscriptionsController(InscriptionsModel inscriptionsModel) {
        this.inscriptionsModel = inscriptionsModel;
    }

    public Inscription create(Inscription baseInscription) {
        return this.inscriptionsModel.create(baseInscription);
    }

    public List<Inscription> findAll(User user) {
        return this.inscriptionsModel.findAll(user);
    }
}

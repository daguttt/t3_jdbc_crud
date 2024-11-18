package org.example.controllers;

import org.example.entities.User;
import org.example.models.UsersModel;

public class UsersController {
    private final UsersModel usersModel;

    public UsersController(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public User login(User user) {
        return this.usersModel.login(user);
    }

    public User register(User user) {
        return this.usersModel.register(user);
    }
}

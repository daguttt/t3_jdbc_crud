package org.example.controllers;

import org.example.entities.User;
import org.example.models.UserModel;

public class UsersController {
    private final UserModel userModel;

    public UsersController(UserModel userModel) {
        this.userModel = userModel;
    }

    public User login(User user) {
        return this.userModel.login(user);
    }

    public User register(User user) {
        return this.userModel.register(user);
    }
}

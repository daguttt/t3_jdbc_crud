package org.example;

import org.example.controllers.UsersController;
import org.example.models.UserModel;
import org.example.persistence.Database;
import org.example.utils.InputRequester;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("***********************************************************");
        System.out.println("Workshop JDBC + MySQL CRUD App");
        System.out.println("***********************************************************");

        String host = args[0];
        String port = args[1];
        String dbName = args[2];
        String dbUser = args[3];
        String dbPassword = args[4];

        // -****************************
        // Dependency Injection
        var database = new Database(host, port, dbName, dbUser, dbPassword);
        database.testConnection();

        // Models
        UserModel userModel = new UserModel(database);


        // Controller
        UsersController usersController = new UsersController(userModel);


        // -****************************

        // Menu
        var appState = new AppState();
        var menuOptionsFactory = new MenuOptionsFactory(
                appState,
                usersController
        );
        var authMenu = new Menu(menuOptionsFactory.getAuthMenuCommands());
        var mainMenu = new Menu(menuOptionsFactory.getMainMenuCommands());

        while (Objects.equals(appState.getStatus(), AppStatus.RUNNING)) {
            if (appState.getUser() == null)
                authMenu.display();
            mainMenu.display();
        }

    }
}
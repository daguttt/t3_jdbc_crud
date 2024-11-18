package org.example;

import org.example.controllers.ProjectsController;
import org.example.controllers.UsersController;
import org.example.models.ProjectsModel;
import org.example.models.UsersModel;
import org.example.persistence.Database;

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
        UsersModel usersModel = new UsersModel(database);
        ProjectsModel projectsModel = new ProjectsModel(database);


        // Controller
        UsersController usersController = new UsersController(usersModel);
        ProjectsController projectsController = new ProjectsController(projectsModel);


        // -****************************

        // Menu
        var appState = new AppState();
        var menuOptionsFactory = new MenuOptionsFactory(
                appState,
                usersController,
                projectsController
        );
        Menu authMenu = new Menu(menuOptionsFactory.getAuthMenuCommands());
        Menu mainMenu = new Menu(menuOptionsFactory.getMainMenuCommands());

        authMenu.subMenu(mainMenu).open();
    }
}
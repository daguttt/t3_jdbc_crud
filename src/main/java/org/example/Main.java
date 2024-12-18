package org.example;

import org.example.controllers.InscriptionsController;
import org.example.controllers.ProjectsController;
import org.example.controllers.UsersController;
import org.example.models.InscriptionsModel;
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
        InscriptionsModel inscriptionsModel = new InscriptionsModel(database);


        // Controller
        UsersController usersController = new UsersController(usersModel);
        ProjectsController projectsController = new ProjectsController(projectsModel);
        InscriptionsController inscriptionsController = new InscriptionsController(inscriptionsModel);


        // -****************************

        // Menu
        var appState = new AppState();
        var menuOptionsFactory = new MenuOptionsFactory(
                appState,
                usersController,
                projectsController,
                inscriptionsController

        );
        Menu authMenu = new Menu(menuOptionsFactory.getAuthMenuCommands());
        Menu publisherMenu = new Menu(menuOptionsFactory.getPublisherMenuCommands());
        Menu volunteerMenu = new Menu(menuOptionsFactory.getVolunteerMenuCommands());

        while (appState.getStatus() == AppState.Status.RUNNING) {
            if (appState.getUser() == null) authMenu.open();
            if (appState.getStatus() == AppState.Status.RUNNING)
                switch (appState.getUser().getRole()) {
                    case PUBLISHER -> publisherMenu.open();
                    case VOLUNTEER -> volunteerMenu.open();
                }
        }
    }
}
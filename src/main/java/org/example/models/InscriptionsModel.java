package org.example.models;

import org.example.entities.Inscription;
import org.example.entities.Project;
import org.example.entities.User;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class InscriptionsModel {
    private final Database database;

    public InscriptionsModel(Database database) {
        this.database = database;
    }

    public Inscription create(Inscription baseInscription) {
        var sql = """
                INSERT INTO inscriptions (user_id, project_id, date)\s
                    VALUES (?, ?, ?);
                """;

        try (
                var connection = database.openConnection();
                var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, baseInscription.getUser().getId());
            statement.setInt(2, baseInscription.getProject().getId());
            statement.setDate(3, baseInscription.getDate());

            statement.execute();

            var resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int givenInscriptionId = resultSet.getInt(1);
                baseInscription.setId(givenInscriptionId);
            } else throw new SQLException("Couldn't create inscriptions");

            resultSet.close();

            return baseInscription;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Inscription> findAll(User user) {
        var sql = """
                SELECT i.id, p.title as project_title, i.date\s
                FROM inscriptions as i\s
                INNER JOIN projects as p\s
                ON i.project_id = p.id
                WHERE i.user_id = ?;
                """;

        var inscriptionList = new ArrayList<Inscription>();

        try (
                var connection = database.openConnection();
                var statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, user.getId());

            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var projectTitle = resultSet.getString("project_title");
                var date = resultSet.getDate("date");

                var project = new Project();
                project.setTitle(projectTitle);

                var inscription = new Inscription(id, user, project, date);
                inscriptionList.add(inscription);
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return inscriptionList.stream().toList();

    }
}

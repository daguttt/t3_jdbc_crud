package org.example.models;

import org.example.entities.Inscription;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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

}

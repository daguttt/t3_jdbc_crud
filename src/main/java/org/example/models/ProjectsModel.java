package org.example.models;

import org.example.entities.Project;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectsModel {
    private final Database database;

    public ProjectsModel(Database database) {
        this.database = database;
    }

    public Project create(Project baseProject) {
        var sql = """
                INSERT INTO projects (title, description, start_date, end_date, created_by)\s
                    VALUES (?, ?, ?, ?, ?);
                """;

        try (
                var connection = database.openConnection();
                var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, baseProject.getTitle());
            statement.setString(2, baseProject.getDescription());
            statement.setDate(3, baseProject.getStartDate());
            statement.setDate(4, baseProject.getEndDate());
            statement.setInt(5, baseProject.getCreatedById());

            statement.execute();

            var resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int givenProjectId = resultSet.getInt(1);
                baseProject.setId(givenProjectId);
            } else throw new SQLException("Couldn't create project");

            resultSet.close();

            return baseProject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

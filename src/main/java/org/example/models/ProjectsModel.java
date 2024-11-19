package org.example.models;

import org.example.entities.Project;
import org.example.entities.User;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            statement.setInt(5, baseProject.getCreatedBy().getId());

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

    public List<Project> findAll() {

        var sql = """
                SELECT p.id, p.title, p.description, p.start_date, p.end_date, u.name as creator_name\s
                FROM projects as p\s
                INNER JOIN users as u\s
                ON p.created_by = u.id;
                """;

        var projectList = new ArrayList<Project>();

        try (
            var connection = database.openConnection();
            var statement = connection.createStatement())
        {

            var resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var startDate = resultSet.getDate("start_date");
                var endDate = resultSet.getDate("end_date");
                var creatorName = resultSet.getString("creator_name");

                var creator = new User();
                creator.setName(creatorName);

                var project = new Project(id, title, description, startDate, endDate, creator);
                projectList.add(project);
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projectList.stream().toList();

    }
}

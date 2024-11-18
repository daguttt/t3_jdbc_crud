package org.example.models;

import org.example.entities.User;
import org.example.enums.Roles;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {
    private final Database database;

    public UserModel(Database database) {
        this.database = database;
    }

    public User login(User user) {
        var sql = """
                SELECT * FROM users WHERE email = ? AND password = ?
                """;
        User loggedUser = null;
        try (
                var con = database.openConnection();
                var statement = con.prepareStatement(sql)
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            var result = statement.executeQuery();
            if (result.next()) {
                loggedUser = new User(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password"),
                        Roles.valueOf(result.getString("role"))
                );
            }
            return loggedUser;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User register(User baseUser) {
        var sql = """
                INSERT INTO users (name, email, password, role)\s
                    VALUES (?, ?, ?, ?);
                """;

        try (
            var connection = database.openConnection();
            var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, baseUser.getName());
            statement.setString(2, baseUser.getEmail());
            statement.setString(3, baseUser.getPassword());
            statement.setString(4, baseUser.getRole().name());

            statement.execute();

            var resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int givenUserId = resultSet.getInt(1);
                baseUser.setId((long) givenUserId);
            } else throw new SQLException("Couldn't create enrollment");

            resultSet.close();

            return baseUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

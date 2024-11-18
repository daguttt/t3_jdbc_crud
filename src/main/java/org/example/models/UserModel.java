package org.example.models;

import org.example.entities.User;
import org.example.enums.Roles;
import org.example.persistence.Database;

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

    public User register(User user) {
        return null;
    }
}

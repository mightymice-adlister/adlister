package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection = null;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }


    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("day_number"),
            rs.getString("evening_number"),
            rs.getString("bio")
        );
    }

    @Override
    public void updateProfile(User user, String newEmail, String firstName, String lastName, String dayNumber, String eveningNumber, String bio){
        String query = "UPDATE users SET email = ?, first_name = ?, last_name = ?, day_number = ?, evening_number = ?, bio = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newEmail);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, dayNumber);
            stmt.setString(5, eveningNumber);
            stmt.setString(6, bio);
            stmt.setLong(7, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update profile", e);
        }

    }


    public void changePassword(User user, String newPassword){
        newPassword = Password.hash(newPassword);
        String query = "UPDATE users SET password = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newPassword);
            stmt.setLong(2, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to change password", e);
        }

    }
}

package app.dao;

import app.model.User;
import app.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class UserDAO {

    // Insert a new user into the DB
    public boolean insertUser(User user) {
        String sql = "INSERT INTO users(username, email, password_hash) VALUES(?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());

            int rows = stmt.executeUpdate();
            return rows > 0; // success if at least one row inserted

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Find a user by username
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // not found
    }



}

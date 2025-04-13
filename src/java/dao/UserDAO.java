/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author SHD
 */
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class UserDAO extends DBContext {

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE Email = ? AND PasswordHash = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setString(2, password);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("UserId"),
                            rs.getString("UserName"),
                            rs.getString("PasswordHash"),
                            rs.getString("Email"),
                            rs.getString("Role"),
                            rs.getBoolean("IsActive"),
                            rs.getTimestamp("CreatedAt")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    public boolean registerUser(User user) {
        try {
            String sql = "INSERT INTO Users (UserName, Email, PasswordHash, Role, IsActive, CreatedAt) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            stm.setString(4, user.getRole());
            stm.setBoolean(5, true);
            stm.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            int rowsInserted = stm.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean isEmailExists(String email) {
        try {
            String sql = "SELECT * FROM Users WHERE Email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("UserId");
                String userName = rs.getString("UserName");
                String email = rs.getString("Email");
                String password = rs.getString("PasswordHash");
                String role = rs.getString("Role");
                boolean isActive = rs.getBoolean("IsActive");
                User user = new User(userId, userName, email, password, role, isActive);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE UserId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String userName = rs.getString("UserName");
                String email = rs.getString("Email");
                String password = rs.getString("PasswordHash");
                String role = rs.getString("Role");
                boolean isActive = rs.getBoolean("IsActive");
                return new User(userId, userName, email, password, role, isActive);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO Users (UserName, Email, PasswordHash, Role, IsActive) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            stm.setString(4, user.getRole());
            stm.setBoolean(5, user.isIsActive());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET UserName = ?, PasswordHash = ?, Role = ?, IsActive = ? WHERE UserId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user.getUserName());

            stm.setString(2, user.getPassword());
            stm.setString(3, user.getRole());
            stm.setBoolean(4, user.isIsActive());
            stm.setInt(5, user.getUserId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println("hi");
    }
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE UserId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, userId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean disableUser(int userId) {
        String sql = "Update Users set IsActive = 0 WHERE UserId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, userId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean activeUser(int userId) {
        String sql = "Update Users set IsActive = 1 WHERE UserId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, userId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

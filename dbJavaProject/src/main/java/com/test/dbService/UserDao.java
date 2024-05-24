package com.test.dbService;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.model.User;

public class UserDao {
    
    public void createUser(User user){
        String sql = "INSERT INTO User (username, password, email, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setDouble(4, user.getBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM User WHERE user_id = ?";
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUtenti() {
        String sql = "SELECT * FROM User";
        List<User> utenti = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getDouble("balance"));
                utenti.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    public void updateUtente(User user) {
        String sql = "UPDATE User SET username = ?, email = ?  WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setInt(3, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public void deleteUtente(int id) {
        String sql = "DELETE FROM User WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setBalance(User user) {
        String sql = "UPDATE User SET balance = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Log input values for debugging
            System.out.println("Updating balance for user_id: " + user.getId() + " with balance: " + user.getBalance());
            
            pstmt.setDouble(1, user.getBalance());
            pstmt.setInt(2, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update successful for user_id: " + user.getId());
            } else {
                System.out.println("No user found with user_id: " + user.getId());
            }
            
        } catch (SQLException e) {
            System.err.println("SQL error while updating balance: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error while updating balance: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
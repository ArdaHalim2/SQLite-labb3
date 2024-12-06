package se.iths;

import se.iths.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class PlayerManager {

    // Lägga till en spelare
    public void addPlayer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter player name: ");
        String name = scanner.nextLine();

        System.out.print("Enter high score: ");
        int highScore = scanner.nextInt();

        System.out.print("Enter GameID (the game the player is playing): ");
        int gameID = scanner.nextInt();

        String sql = "INSERT INTO Players (Name, HighScore, GameID) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, highScore);
            pstmt.setInt(3, gameID);
            pstmt.executeUpdate();
            System.out.println("Player added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Visa alla spelare
    public void showPlayers() {
        String sql = "SELECT * FROM Players";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("PlayerID") +
                        ", Name: " + rs.getString("Name") +
                        ", HighScore: " + rs.getInt("HighScore") +
                        ", GameID: " + rs.getInt("GameID"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Uppdatera en spelare
    public void updatePlayer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the PlayerID to update: ");
        int playerID = scanner.nextInt();
        scanner.nextLine();  // För att konsumera den extra ny rad

        System.out.print("Enter new player name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new high score: ");
        int highScore = scanner.nextInt();

        System.out.print("Enter new GameID (the game the player is playing): ");
        int gameID = scanner.nextInt();

        String sql = "UPDATE Players SET Name = ?, HighScore = ?, GameID = ? WHERE PlayerID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, highScore);
            pstmt.setInt(3, gameID);
            pstmt.setInt(4, playerID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player updated successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Ta bort en spelare
    public void deletePlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the PlayerID to delete: ");
        int playerID = scanner.nextInt();

        String sql = "DELETE FROM Players WHERE PlayerID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player deleted successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

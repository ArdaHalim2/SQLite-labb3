package se.iths;

import se.iths.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class PlayerManager {

    // Metod för att lägga till en ny spelare
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

    // Metod för att visa alla spelare
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
}

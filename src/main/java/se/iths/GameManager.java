package se.iths;

import java.sql.*;
import java.util.Scanner;

public class GameManager {
    public void addGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter game title: ");
        String title = scanner.nextLine();

        System.out.print("Enter game genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter release year: ");
        int year = scanner.nextInt();

        String sql = "INSERT INTO Games (Title, Genre, ReleaseYear) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, genre);
            pstmt.setInt(3, year);
            pstmt.executeUpdate();
            System.out.println("Game added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void showGames() {
        String sql = "SELECT * FROM Games";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("GameID") +
                        ", Title: " + rs.getString("Title") +
                        ", Genre: " + rs.getString("Genre") +
                        ", Release Year: " + rs.getInt("ReleaseYear"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void showGamesWithPlayers() {
        String sql = "SELECT Games.Title, Players.Name, Players.HighScore " +
                "FROM Games JOIN Players ON Games.GameID = Players.GameID";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Game: " + rs.getString("Title") +
                        ", Player: " + rs.getString("Name") +
                        ", HighScore: " + rs.getInt("HighScore"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

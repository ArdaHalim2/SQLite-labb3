package se.iths;

import java.sql.*;
import java.util.Scanner;

public class GameManager {

    // Lägga till ett nytt spel
    public void addGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter game title: ");
        String title = scanner.nextLine();

        System.out.print("Enter game genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter release year: ");
        int releaseYear = scanner.nextInt();

        String sql = "INSERT INTO Games (Title, Genre, ReleaseYear) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, genre);
            pstmt.setInt(3, releaseYear);
            pstmt.executeUpdate();
            System.out.println("Game added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Visa alla spel
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

    // Uppdatera ett spel
    public void updateGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the GameID to update: ");
        int gameID = scanner.nextInt();
        scanner.nextLine();  // För att konsumera den extra ny rad

        System.out.print("Enter new game title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter new release year: ");
        int releaseYear = scanner.nextInt();

        String sql = "UPDATE Games SET Title = ?, Genre = ?, ReleaseYear = ? WHERE GameID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, genre);
            pstmt.setInt(3, releaseYear);
            pstmt.setInt(4, gameID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Game updated successfully.");
            } else {
                System.out.println("Game not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Ta bort ett spel
    public void deleteGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the GameID to delete: ");
        int gameID = scanner.nextInt();

        String sql = "DELETE FROM Games WHERE GameID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, gameID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Game deleted successfully.");
            } else {
                System.out.println("Game not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Visa spel med spelare via JOIN
    public void showGamesWithPlayers() {
        String sql = "SELECT g.GameID, g.Title, p.Name FROM Games g " +
                "JOIN Players p ON g.GameID = p.GameID";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Game ID: " + rs.getInt("GameID") +
                        ", Game Title: " + rs.getString("Title") +
                        ", Player: " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

package se.iths;

import se.iths.GameManager;
import se.iths.PlayerManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        PlayerManager playerManager = new PlayerManager();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMENU");
            System.out.println("1. Add Game");
            System.out.println("2. Show Games");
            System.out.println("3. Add Player");
            System.out.println("4. Show Games with Players");
            System.out.println("5. Show Players");
            System.out.println("6. Update Game");
            System.out.println("7. Update Player");
            System.out.println("8. Delete Game");
            System.out.println("9. Delete Player");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> gameManager.addGame();
                case 2 -> gameManager.showGames();
                case 3 -> playerManager.addPlayer();
                case 4 -> gameManager.showGamesWithPlayers();
                case 5 -> playerManager.showPlayers();
                case 6 -> gameManager.updateGame();
                case 7 -> playerManager.updatePlayer();
                case 8 -> gameManager.deleteGame();
                case 9 -> playerManager.deletePlayer();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}

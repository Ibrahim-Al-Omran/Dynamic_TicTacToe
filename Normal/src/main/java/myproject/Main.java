package myproject;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Select game mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        System.out.println("3. Computer vs Computer");

        int choice = 0;
        boolean good = false;

        // Input validation for selecting game mode
        while (!good) {
            try {
                System.out.println("Enter your choice (1, 2, or 3):");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    good = true;
                } else {
                    System.out.println("Error -- Please enter 1, 2, or 3");
                }
            } catch (Exception e) {
                System.out.println("Error -- Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }

        Player player1 = null;
        Player player2 = null;

        // Initialize players based on game mode
        if (choice == 1) {
            player1 = new Type('X', "H"); // Human player with 'X'
            player2 = new Type('O', "H"); // Human player with 'O'
        } else if (choice == 2) {
            player1 = new Type('X', "H"); // Human player with 'X'
            player2 = new Type('O', "C"); // Computer player with 'O'
        } else if (choice == 3) {
            player1 = new Type('X', "C"); // Computer player with 'X'
            player2 = new Type('O', "C"); // Computer player with 'O'
        }

        // Start the game with the selected players
        Game game = new Game(player1, player2);
        game.startGame();
    }
}

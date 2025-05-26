package myproject;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        int size;
        while (true) {
            System.out.print("Enter the grid size (N, 3 <= N <= 20): ");
            size = scanner.nextInt();
            if (size >= 3 && size <= 20) {
                break;
            }
            System.out.println("Error -- Please enter a value between 3 and 20");
        }

        boolean good1 = false;
        int win = 0;
        while (!good1) {
            System.out.printf("Enter the number of markers in a row needed to win (M, where 3 <= M <= %d): ", size);
            win = scanner.nextInt();
            if (win >= 3 && win <= size) {
                good1 = true;
            }
            System.out.printf("Invalid winning condition. Please ensure 3 <= M <= %d.\n", size);
        }

        System.out.println("Select game mode:");
        System.out.println("1.Human vs Human");
        System.out.println("2.Human vs Computer");
        System.out.println("3.Computer vs Computer");

        int choice = 0;
        boolean good2 = false;

        while (!good2) {
            try {
                System.out.println("Enter your choice (1, 2 or 3)");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    good2 = true;
                } else {
                    System.out.println("Error -- Please enter 1, 2, or 3");
                }
            } catch (Exception e) {
                System.out.println("Error -- Please enter a valid number.");
                scanner.next(); 
            }
        }

        Player player1 = null;
        Player player2 = null;

        if (choice == 1) {
            player1 = new Type('X', "H");
            player2 = new Type('O', "H");
        } else if (choice == 2) {
            player1 = new Type('X', "H");
            player2 = new Smart('O', win);
        } else if (choice == 3) {
            player1 = new Smart('X', win);
            player2 = new Smart('O', win);
        }

        BigGame game = new BigGame(size, win, player1, player2);
        game.startGame();
    }
}

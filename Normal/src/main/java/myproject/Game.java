package myproject;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;

    // Initializes the game with two players
    public Game(Player player1, Player player2) {
        board = new Board();
        this.player1 = player1;
        this.player2 = player2;
    }

    // Starts and manages the game loop
    public void startGame() {
        System.out.printf("Player 1: %s\n", player1.identifyUser());
        System.out.printf("Player 2: %s\n", player2.identifyUser());
        board.displayBoard();
        int count = 0;

        // Game loop for up to 9 moves
        while (count < 9) {
            Player currentPlayer = (count % 2 == 0) ? player1 : player2;
            char symbol = currentPlayer.getSymbol();

            // Handle the current player's move
            if (makeMove(currentPlayer, symbol)) {
                continue; // Skip incrementing count for invalid moves
            }

            // Check if the current player wins
            if (board.checkWin(symbol)) {
                System.out.printf("%s wins!\n", currentPlayer.getSymbol());
                break;
            }

            count++;
        }

        // Check for a draw
        if (count == 9) {
            System.out.println("The game has reached a draw.");
        }

        gameDone();
    }

    // Handles a player's move
    private boolean makeMove(Player player, char symbol) {
        int row, column;

        if (player.isHuman()) {
            System.out.printf("%s, choose your row (1 to 3) and column (1 to 3) (input as row,column):\n", player.getSymbol());
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            String[] parts = input.split(",");

            // Validate input format
            if (parts.length != 2) {
                System.out.println("Error -- invalid input");
                return true;
            }

            try {
                row = Integer.parseInt(parts[0].trim()) - 1;
                column = Integer.parseInt(parts[1].trim()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Error -- invalid numbers");
                return true;
            }
        } else {
            // Computer makes its move
            player.move(board);
            return false;
        }

        // Validate and place the move
        if (board.checkInput(row, column)) {
            board.place(row, column, symbol);
            board.displayBoard();
        } else {
            System.out.println("Error -- Invalid move. Try again.");
            return true;
        }

        return false;
    }

    // Handles the end-of-game sequence
    private void gameDone() {
        board.reset();
        System.out.println("Would you like to restart? Please type in 'Yes' or 'No'");
        Scanner sc1 = new Scanner(System.in);
        String input = sc1.next();
        String answer = input.toLowerCase();

        // Handle restart or exit based on user input
        switch (answer) {
            case "yes":
                startGame();
                break;
            case "no":
                System.out.println("Thank you for playing!");
                System.exit(0);
                break;
            default:
                System.out.println("Error -- bad input. Please type 'Yes' or 'No'.");
                gameDone();
                break;
        }
    }
}

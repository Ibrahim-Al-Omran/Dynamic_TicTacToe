package myproject;

import java.util.Scanner;

public class BigGame extends Game {
    private BigBoard bigBoard;
    private int win;

    public BigGame(int size, int win, Player player1, Player player2) {
        super(player1, player2); // Initialize players with the parent constructor
        this.bigBoard = new BigBoard(size);
        this.win = win; // Winning condition
    }

    @Override
    public void startGame() {
        System.out.printf("Player 1: %s\n", player1.identifyUser());
        System.out.printf("Player 2: %s\n", player2.identifyUser());
        bigBoard.displayBoard();

        int maxMoves = bigBoard.getSize() * bigBoard.getSize(); // Total moves possible
        int moveCount = 0;

        while (moveCount < maxMoves) {
            Player currentPlayer = (moveCount % 2 == 0) ? player1 : player2;
            char symbol = currentPlayer.getSymbol();

            if (handleMove(currentPlayer, symbol)) {
                continue; // Retry if move was invalid
            }

            if (bigBoard.checkWin(symbol, win)) {
                System.out.printf("%s wins!\n", currentPlayer.getSymbol());
                break;
            }
            moveCount++;
        }

        if (moveCount == maxMoves) {
            System.out.println("It's a draw!");
        }

        promptRestart();
    }

    private boolean handleMove(Player player, char symbol) {
        // Handles move input and validation
        int row, col;

        if (player.isHuman()) {
            System.out.printf("%s, enter your move (row,column):\n", player.getSymbol());
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine(); // Use nextLine for flexibility

            String[] parts = input.split(",");
            if (parts.length != 2) {
                System.out.println("Invalid input format. Use row,column.");
                return true;
            }

            try {
                row = Integer.parseInt(parts[0].trim()) - 1;
                col = Integer.parseInt(parts[1].trim()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid numbers. Try again.");
                return true;
            }
        } else {
            player.move(bigBoard); // Let the computer decide
            return false;
        }

        if (bigBoard.checkInput(row, col)) {
            bigBoard.place(row, col, symbol);
            bigBoard.displayBoard();
        } else {
            System.out.println("Invalid move. Cell occupied or out of bounds.");
            return true;
        }
        return false;
    }

    private void promptRestart() {
        // Handle game restart or exit
        bigBoard.reset();
        System.out.println("Restart the game? Type 'Yes' or 'No':");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim().toLowerCase();

        switch (input) {
            case "yes":
                startGame(); // Restart the game
                break;
            case "no":
                System.out.println("Thanks for playing!"); // Exit
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Type 'Yes' or 'No'.");
                promptRestart(); // Retry input
                break;
        }
    }
}

package myproject;

// Manages the 3x3 grid and updates it by placing markers
public class Board {
    private char[][] grid;

    // Initializes a 3x3 board with empty cells ('-')
    public Board() {
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '-';
            }
        }
    }

    // Displays the current board
    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) System.out.print(" ");
                System.out.print(grid[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("-----------");
        }
    }

    // Places a marker for the given player at the specified location
    public void place(int row, int column, char player) {
        grid[row][column] = player;
    }

    // Validates input to ensure the cell is within bounds and unoccupied
    public boolean checkInput(int row, int column) {
        if (row > 2 || column > 2 || row < 0 || column < 0) {
            System.out.println("Error -- Row/column out of bounds");
            return false;
        }
        return grid[row][column] == '-';
    }

    // Checks if the given player has won the game
    public boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if ((grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) || // Row check
                (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player)) { // Column check
                return true;
            }
        }
        // Diagonal check
        return (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) ||
               (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player);
    }

    // Returns the grid
    public char[][] getGrid() {
        return grid;
    }

    // Resets the board to its initial state
    public char[][] reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '-';
            }
        }
        return grid;
    }
}

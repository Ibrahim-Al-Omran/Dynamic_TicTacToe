package myproject;

public class BigBoard extends Board {
    private final char[][] grid; // The game board
    private final int size; // Board dimensions (size x size)

    public BigBoard(int size) {
        super();
        this.size = size;
        this.grid = new char[size][size];
        reset();
    }

    @Override
    public void displayBoard() {
        // Print the board 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j]);
                if (j < size - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < size - 1) {
                for (int k = 0; k < size * 4 - 1; k++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }

    @Override
    public boolean checkInput(int row, int col) {
        // Validate whether a cell is empty and within bounds
        if(row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == '-') {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void place(int row, int col, char symbol) {
        // Place a player's symbol on the board
        if (checkInput(row, col)) {
            grid[row][col] = symbol;
        }
        else{
            System.out.println("Error -- Row/column out of bounds");
        }
    }

    public boolean checkWin(char player, int win) {
        // Check horizontal and vertical lines
        for (int i = 0; i < size; i++) {
            // Check horizontal
            if (checkLine(player, win, i, 0, 0, 1)) {
                return true;
            }
            // Check vertical
            if (checkLine(player, win, 0, i, 1, 0)) {
                return true;
            }
        }
        // Check diagonals (both directions)
        if (checkDiagonals(player, win)) {
            return true;
        }
        return false;
    }
    
    private boolean checkLine(char player, int win, int startRow, int startCol, int rowIncrement, int colIncrement) {
        int count = 0;
        for (int step = 0; step < win; step++) {
            int row = startRow + step * rowIncrement;
            int col = startCol + step * colIncrement;
    
            // Check if we are within bounds and the symbol matches
            if (row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == player) {
                count++;
            } else {
                break;
            }
        }
        return count == win;
    }
    
    private boolean checkDiagonals(char player, int win) {
        // Check all diagonals across the board
        for (int i = 0; i <= size - win; i++) {
            for (int j = 0; j <= size - win; j++) {
                // Check diagonal from top-left to bottom-right
                if (checkLine(player, win, i, j, 1, 1)) {
                    return true;
                }
                // Check diagonal from top-right to bottom-left
                if (checkLine(player, win, i, j + win - 1, 1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    

    public char[][] getGrid() {
        return grid;
    }

    @Override
    public char[][] reset() {
        // Reset the board to its default state
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = '-';
            }
        }
        return grid;
    }

    public int getSize() {
        return size;
    }
}

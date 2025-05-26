package myproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Smart extends Player {
    private final int win; // Required number of symbols for victory

    public Smart(char symbol, int win) {
        super(symbol);
        this.win = win;
    }

    @Override
    public String identifyUser() {
        return "Smart Computer";
    }

    @Override
    public boolean isHuman() {
        return false; 
    }

    @Override
    public void move(Board board) {
        // Try strategies in priority order
        if (tryWinning(board) || tryBlocking(board) || takeCenter(board) || takeCornerOrEdge(board)) {
            return;
        }
        randomMove(board); // Fallback to a random move if no strategy applies
    }

    private void randomMove(Board board) {
        char[][] grid = board.getGrid();
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(grid.length);
            col = random.nextInt(grid.length);
        } while (!board.checkInput(row, col));

        board.place(row, col, getSymbol());
        System.out.printf("Computer played: (%d, %d)\n", row + 1, col + 1);
        board.displayBoard();
    }

    private boolean tryWinning(Board board) {
        return evaluateLines(board, getSymbol());
    }

    private boolean tryBlocking(Board board) {
        char opponentSymbol = (getSymbol() == 'X') ? 'O' : 'X';
        return evaluateLines(board, opponentSymbol);
    }

    private boolean evaluateLines(Board board, char symbol) {
        char[][] grid = board.getGrid();

        for (int i = 0; i < grid.length; i++) {
            if (attemptPlacement(board, i, 0, 0, 1, grid.length, symbol) || // Check rows
                attemptPlacement(board, 0, i, 1, 0, grid.length, symbol)) { // Check columns
                return true;
            }
        }
        return false;
    }

    private boolean attemptPlacement(Board board, int startRow, int startCol, int rowInc, int colInc, int size, char symbol) {
        int count = 0, emptyRow = -1, emptyCol = -1;
        char[][] grid = board.getGrid();

        for (int i = 0; i < size; i++) {
            int row = startRow + i * rowInc;
            int col = startCol + i * colInc;

            if (row >= size || col >= size || row < 0 || col < 0) break;

            if (grid[row][col] == symbol) count++;
            else if (grid[row][col] == '-') {
                emptyRow = row;
                emptyCol = col;
            }
        }

        if (count == win - 1 && emptyRow != -1) {
            board.place(emptyRow, emptyCol, getSymbol());
            System.out.printf("Computer played: (%d, %d)\n", emptyRow + 1, emptyCol + 1);
            board.displayBoard();
            return true;
        }
        return false;
    }

    private boolean takeCenter(Board board) {
        char[][] grid = board.getGrid();
        int center = grid.length / 2;

        if (grid.length % 2 != 0 && board.checkInput(center, center)) {
            board.place(center, center, getSymbol());
            System.out.printf("Computer played: (%d, %d)\n", center + 1, center + 1);
            board.displayBoard();
            return true;
        }
        return false;
    }

    private boolean takeCornerOrEdge(Board board) {
        List<int[]> moves = new ArrayList<>();
        char[][] grid = board.getGrid();
        int size = grid.length;

        moves.add(new int[]{0, 0}); // Top-left
        moves.add(new int[]{0, size - 1}); // Top-right
        moves.add(new int[]{size - 1, 0}); // Bottom-left
        moves.add(new int[]{size - 1, size - 1}); // Bottom-right

        for (int i = 1; i < size - 1; i++) {
            moves.add(new int[]{0, i}); // Top edge
            moves.add(new int[]{size - 1, i}); // Bottom edge
            moves.add(new int[]{i, 0}); // Left edge
            moves.add(new int[]{i, size - 1}); // Right edge
        }

        for (int[] move : moves) {
            if (board.checkInput(move[0], move[1])) {
                board.place(move[0], move[1], getSymbol());
                System.out.printf("Computer played: (%d, %d)\n", move[0] + 1, move[1] + 1);
                board.displayBoard();
                return true;
            }
        }
        return false;
    }
}

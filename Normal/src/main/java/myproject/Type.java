package myproject;
import java.util.Random;

class Type extends Player {
    private String playerType;

    public Type(char symbol, String playerType){
        super(symbol);
        this.playerType = playerType.equals("H") ? "Human" : "Computer";
    }

    public String identifyUser() {
        return playerType;
    }

    public boolean isHuman() {
        return playerType.equals("Human");
    }

    public void move(Board board) {
        if (playerType.equals("Human")) {
            return; // Humans make their moves manually
        }

        // Execute strategy in priority order
        if (attemptWin(board)) return; 
        if (block(board)) return;
        if (center(board)) return;
        if (corner(board)) return;

        // Fallback to a random move
        random(board);
    }

    private void random(Board board) {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!board.checkInput(row, col));

        board.place(row, col, getSymbol());
        System.out.printf("Computer played: (%d, %d)\n", row + 1, col + 1);
        board.displayBoard();
    }

    private boolean attemptWin(Board board) {
        return fill(board, getSymbol());
    }

    private boolean block(Board board) {
        char opponentSymbol = getSymbol() == 'X' ? 'O' : 'X';
        return fill(board, opponentSymbol);
    }

    private boolean fill(Board board, char symbol) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (place(board, i, 0, i, 1, i, 2, symbol)) return true;
            if (place(board, 0, i, 1, i, 2, i, symbol)) return true;
        }
        if (place(board, 0, 0, 1, 1, 2, 2, symbol)) return true;
        if (place(board, 0, 2, 1, 1, 2, 0, symbol)) return true;
        return false;
    }

    private boolean place(Board board, int r1, int c1, int r2, int c2, int r3, int c3, char symbol) {
        // Check and fill a winning or blocking position
        char[][] grid = board.getGrid();
        if (grid[r1][c1] == symbol && grid[r2][c2] == symbol && board.checkInput(r3, c3)) {
            board.place(r3, c3, getSymbol());
            System.out.printf("Computer played: (%d, %d)\n", r3 + 1, c3 + 1);
            board.displayBoard();
            return true;
        }
        if (grid[r1][c1] == symbol && grid[r3][c3] == symbol && board.checkInput(r2, c2)) {
            board.place(r2, c2, getSymbol());
            System.out.printf("Computer played: (%d, %d)\n", r2 + 1, c2 + 1);
            board.displayBoard();
            return true;
        }
        if (grid[r2][c2] == symbol && grid[r3][c3] == symbol && board.checkInput(r1, c1)) {
            board.place(r1, c1, getSymbol());
            System.out.printf("Computer played: (%d, %d)\n", r1 + 1, c1 + 1);
            board.displayBoard();
            return true;
        }
        return false;
    }

    private boolean center(Board board) {
        // Attempt to take the center position
        int center = 1;
        if (board.checkInput(center, center)) {
            board.place(center, center, getSymbol());
            System.out.printf("Computer played: (%d, %d)\n", center + 1, center + 1);
            board.displayBoard();
            return true;
        }
        return false;
    }

    private boolean corner(Board board) {
        // Attempt to take one of the corners
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            if (board.checkInput(corner[0], corner[1])) {
                board.place(corner[0], corner[1], getSymbol());
                System.out.printf("Computer played: (%d, %d)\n", corner[0] + 1, corner[1] + 1);
                board.displayBoard();
                return true;
            }
        }
        return false;
    }
}

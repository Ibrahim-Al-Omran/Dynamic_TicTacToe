package myproject;

public abstract class Player {
    private char symbol; // The player's symbol ('X' or 'O')

    // Constructor to initialize the player's symbol
    public Player(char symbol) {
        this.symbol = symbol;
    }

    // Getter for the player's symbol
    public char getSymbol() {
        return symbol;
    }

    // Abstract method to identify the type of user (e.g., "Human" or "Computer")
    public abstract String identifyUser();

    // Abstract method to check if the player is human
    public abstract boolean isHuman();

    // Abstract method for the player to make a move
    public abstract void move(Board board);
}

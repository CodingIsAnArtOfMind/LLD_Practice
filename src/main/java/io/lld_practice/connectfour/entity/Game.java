package io.lld_practice.connectfour.entity;

public class Game {
    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    Player winner;
    GameState status;
    boolean isGameOver=false;

    public  Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.status=GameState.IN_PROGRESS;
        this.currentPlayer = player1; // Player 1 starts
    }

    public boolean makeMove(Player player, int column) {
        if (isGameOver) {
            System.out.println("Game is already over. No more moves allowed.");
            return false;
        }

        if (player != currentPlayer) {
            System.out.println("It's not " + player + "'s turn.");
            return false;
        }
        //TODO first we need to get the row from the coloum which user give

        int row=board.canWePlace(column,player);
        if (row == -1) {
            return false;
        }

        if (board.checkWinner(currentPlayer,row,column)){
            winner=currentPlayer;
            status=GameState.WINNER;
            isGameOver=true;
        }else if(board.isBoardFull()){
            status=GameState.DRAW;
            isGameOver=true;
        }else {

        // Validate the move (e.g., check if the column is valid and not full)
        // Update the board state
        // Check for win/draw conditions and update status accordingly

        // Switch to the other player for the next turn
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        return true; // Move was successful

    }

    public Board getBoard() {
        return board;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getStatus() {
        return status;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

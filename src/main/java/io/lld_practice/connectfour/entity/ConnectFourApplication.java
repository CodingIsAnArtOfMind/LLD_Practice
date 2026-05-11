package io.lld_practice.connectfour.entity;

import java.util.Scanner;

public class ConnectFourApplication {
    public static void main(String[] args) {
    Player player1 = new Player("Raza", GridColor.BLUE);
    Player player2 = new Player("Raghi", GridColor.RED);
    Board board = new Board();
    Game game = new Game(board, player1, player2);
    Scanner scanner = new Scanner(System.in);
    board.printBoard();
    while (game.getStatus() == GameState.IN_PROGRESS) {
        Player current = game.getCurrentPlayer();
        System.out.print(current.getPlayerName() + " (" + current.getPlayerColor() + ") choose column 0-6: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Please enter a number.");
            scanner.nextLine();
            continue;
        }
        int column = scanner.nextInt();
        scanner.nextLine();
        boolean moved = game.makeMove(current, column);
        if (!moved) {
            System.out.println("Invalid move. Try again.");
            continue;
        }
        board.printBoard();
    }
    if (game.getStatus() == GameState.WINNER) {
        System.out.println("Winner: " + game.getWinner().getPlayerName());
    } else {
        System.out.println("Game draw.");
    }
    }
}

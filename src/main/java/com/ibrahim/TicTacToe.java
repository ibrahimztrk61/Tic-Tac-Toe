package com.ibrahim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicTacToe {

    Players lastPlayer = new Players();
    private char[][] gameBoard = new char[][]{
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};

    public void play(Players player, int row, int column) {
        if (player.getSign() != lastPlayer.getSign()) {

            validateIsEmpty(row, column);
            gameBoard[row][column] = player.getSign();
            lastPlayer.setSign(player.getSign());
            validateTurn(lastPlayer);
            checkWinner(player);

        } else throw new IllegalArgumentException("Not your turn!");

    }

    private void validateTurn(Players player) {
        char turn = 'X';
        if (player.getSign() == 'X') {
            turn = 'O';
        } else
            turn = 'X';
    }

    public void display() {
        for (char[] chars : gameBoard) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private void validateIsEmpty(int row, int column) {
        if (gameBoard[row][column] != ' ') {
            throw new IllegalArgumentException("This position is not empty");
        }

    }

    private char checkRows(Players player) {
        char sign = player.getSign();
        char winner = ' ';
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == sign && gameBoard[i][1] == sign && gameBoard[i][2] == sign) {
                winner = sign;
                break;
            }
        }
        return winner;
    }

    private char checkColumns(Players player) {
        char sign = player.getSign();
        char winner = ' ';
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == sign && gameBoard[1][i] == sign && gameBoard[2][i] == sign) {
                winner = sign;
                break;
            }
        }
        return winner;
    }

    private char checkCrosses(Players player) {
        char sign = player.getSign();
        char winner = ' ';

        if (gameBoard[0][0] == sign && gameBoard[1][1] == sign && gameBoard[2][2] == sign) {
            winner = sign;
        }
        if (gameBoard[0][2] == sign && gameBoard[1][1] == sign && gameBoard[2][0] == sign) {
            winner = sign;
        }
        return winner;
    }

    private void checkWinner(Players player) {
        if (checkColumns(player) == player.getSign()) {
            throw new IllegalArgumentException("Game is over. Winner is " + player.getSign());
        }
        if (checkRows(player) == player.getSign()) {
            throw new IllegalArgumentException("Game is over. Winner is " + player.getSign());
        }
        if (checkCrosses(player) == player.getSign()) {
            throw new IllegalArgumentException("Game is over. Winner is " + player.getSign());
        }

    }


}

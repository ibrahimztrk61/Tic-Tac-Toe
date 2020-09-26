package com.ibrahim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class TicTacToeTest {

    TicTacToe ticTacToe;
    char[][] gameBoard;

    @BeforeEach
    public void setUp() {
        ticTacToe = new TicTacToe();
        gameBoard = ticTacToe.getGameBoard();
    }


    @Test
    public void it_should_create_new_game_board_and_not_be_null() {
        // Assert
        Assertions.assertNotNull(gameBoard);
    }

    @Test
    public void it_should_create_new_game_board_and_length_is_3() {
        // Assert
        Assertions.assertEquals(3, gameBoard.length);
    }

    @Test
    public void it_should_create_new_game_board_with_space_char() {
        // Assert
        for (char[] row : gameBoard) {
            for (char unitSquare : row)
                Assertions.assertEquals(' ', unitSquare);
        }
    }

    @Test
    public void it_should_play_with_player1_successfully() {
        // Arrange
        Players player1 = new Players();
        player1.setSign('X');
        int row = 1;
        int column = 1;

        // Act
        ticTacToe.play(player1, row, column);

        // Assert
        Assertions.assertEquals('X', gameBoard[row][column]);
    }

    @Test
    public void it_should_play_with_player2_successfully() {
        // Arrange
        Players player2 = new Players();
        player2.setSign('O');
        int row = 1;
        int column = 1;

        // Act
        ticTacToe.play(player2, row, column);

        // Assert
        Assertions.assertEquals('O', gameBoard[row][column]);
    }

    @Test
    public void it_should_throws_exception_when_player1_and_player2_play_same_place() {
        // Arrange
        Players player1 = new Players();
        Players player2 = new Players();
        player1.setSign('X');
        player2.setSign('O');
        int row = 0;
        int column = 0;

        // Act
        ticTacToe.play(player1, row, column);

        Throwable throwable = catchThrowable(() -> ticTacToe.play(player2, row, column));

        // Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("This position is not empty");

    }

    @Test
    public void it_should_show_row_winner_and_throw_game_over_exception() {
        // Arrange
        Players player1 = new Players();
        Players player2 = new Players();
        player1.setSign('X');
        player2.setSign('O');

        // Act
        ticTacToe.play(player1, 0, 0);
        ticTacToe.play(player2, 1, 0);
        ticTacToe.play(player1, 0, 1);
        ticTacToe.play(player2, 1, 1);
        ticTacToe.play(player1, 2, 2);

        Throwable throwable = catchThrowable(() -> ticTacToe.play(player2, 1, 2));
        ticTacToe.display();

        // Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("Game is over. Winner is " + player2.getSign());


    }

    @Test
    public void it_should_show_column_winner_and_throw_game_over_exception() {
        // Arrange
        Players player1 = new Players();
        Players player2 = new Players();
        player1.setSign('X');
        player2.setSign('O');

        // Act
        ticTacToe.play(player1, 0, 0);
        ticTacToe.play(player2, 1, 2);
        ticTacToe.play(player1, 1, 0);
        ticTacToe.play(player2, 1, 1);

        Throwable throwable = catchThrowable(() -> ticTacToe.play(player1, 2, 0));
        ticTacToe.display();

        // Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("Game is over. Winner is " + player1.getSign());

    }

    @Test
    public void it_should_show_cross_winner_and_throw_game_over_exception() {
        // Arrange
        Players player1 = new Players();
        Players player2 = new Players();
        player1.setSign('X');
        player2.setSign('O');

        // Act
        ticTacToe.play(player1, 0, 0);
        ticTacToe.play(player2, 1, 0);
        ticTacToe.play(player1, 1, 1);
        ticTacToe.play(player2, 2, 1);

        Throwable throwable = catchThrowable(() -> ticTacToe.play(player1, 2, 2));
        ticTacToe.display();

        // Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("Game is over. Winner is " + player1.getSign());


    }

}

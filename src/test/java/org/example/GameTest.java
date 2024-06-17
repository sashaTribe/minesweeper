package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class GameTest {

    @Test
    public void inputValidReturnsTrue() {
        int x = 4;
        int size = 10;
        final var expected = true;
        final var actual = Game.isInputValid(x,size);
        Assertions.assertTrue(actual, "Game::isInputValid should return true as 4 is greater than 0 and les than size.");

    }

    @Test
    public void inputValidReturnsFalse() {
        int x = 4;
        int size = 4;
        final var expected = false;
        final var actual = Game.isInputValid(x,size);
        Assertions.assertFalse(actual, "Game::isInputValid should return false as 4 equals to size.");

    }

    @Test
    public void allTilesRevealedReturnsTrue() {
        Game game = new Game(4,4);
        NewGrid grid = game.getGrid();
        Tile[][] board = grid.getGrid();
        for (int i=0; i<board.length; i++) {
            for(int j=0; j< board[0].length; j++) {
                board[i][j] = new NumberTile(i, j, 0);
            }
        }
        grid.revealTheWholeBoard(board);
        final var expected = true;
        final var actual = game.areAllTilesRevealed(board);
        Assertions.assertTrue(actual, "Game::isHasAllTilesBeenRevealed should return true if all tiles are revealed.");

    }

    @Test
    public void allTilesRevealedReturnsFalse() {
        Game game = new Game(4,4);
        NewGrid grid = game.getGrid();
        Tile[][] board = grid.getGrid();
        //grid.revealTheWholeBoard(board);
        final var expected = false;
        final var actual = game.areAllTilesRevealed(board);
        Assertions.assertFalse(actual, "Game::isHasAllTilesBeenRevealed should return false if not all tiles are revealed.");

    }

    @Test
    public void testPositionInputPrompt() {
        Game game = new Game(4,4);
        NewGrid grid = game.getGrid();
        Tile[][] board = grid.getGrid();
        for (int i=0; i<board.length; i++) {
            for(int j=0; j< board[0].length; j++) {
                board[i][j] = new NumberTile(i, j, 0);
            }
        }
    }
}

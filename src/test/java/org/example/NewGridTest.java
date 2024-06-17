package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NewGridTest {

    @Test
    public void printGridWhenAllTileIsHidden(){
        NewGrid grid = new NewGrid(3,2);
        Tile[][] board = grid.getGrid();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                board[i][j] = new Tile(i,j);
                board[i][j].setHidden(true);
            }
        }
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                Assertions.assertEquals('H', board[i][j].printVal());
            }
        }
    }

    @Test
    public void printGridWhenAllTileIsNotHidden(){
        NewGrid grid = new NewGrid(3,2);
        Tile[][] board = grid.getGrid();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                board[i][j] = new NumberTile(i,j,0);
                board[i][j].setHidden(false);
            }
        }
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                Assertions.assertEquals('0', board[i][j].printVal());
            }
        }
    }

    @Test
    public void testDFSRevealMethod() {
        NewGrid grid = new NewGrid(3,2);
        Tile[][] board = grid.getGrid();
        board[0][0] = new MineTile(0,0);
        board[0][1] = new MineTile(0,1);
        board[1][2] = new NumberTile(1,3,0);
        board[0][2] = new NumberTile(0,2,0);
        board[1][1] = new NumberTile(1,1,2);
        board[1][0] = new NumberTile(1,0,2);
        grid.setGrid(board);
        grid.dfsReveal(1,2,board);
        grid.setGrid(board);
        board = grid.getGrid();
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board.length; j++) {
                if (board[i][j] instanceof MineTile){
                    Assertions.assertEquals('H', board[i][j]);
                }
            }
        }


    }

    @Test
    public void testDFSRevealMethodForNumberTiles() {
        NewGrid grid = new NewGrid(3, 2);
        Tile[][] board = grid.getGrid();
        board[0][0] = new MineTile(0, 0);
        board[0][1] = new MineTile(0, 1);
        board[1][2] = new NumberTile(1, 3, 0);
        board[0][2] = new NumberTile(0, 2, 0);
        board[1][1] = new NumberTile(1, 1, 2);
        board[1][0] = new NumberTile(1, 0, 2);
        grid.setGrid(board);
        grid.dfsReveal(1, 2, board);
        grid.setGrid(board);
        board = grid.getGrid();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] instanceof NumberTile) {
                    if (((NumberTile) board[i][j]).getNumOfMines() == 0) {
                        Assertions.assertEquals('0', board[i][j]);
                    } else {
                        Assertions.assertEquals('2', board[i][j]);
                    }
                }
            }


        }
    }

    @Test
    public void testFillingOutRestOfBoardNumberTileVals() {
        NewGrid  grid = new NewGrid(3,3);
        Tile[][] board = grid.getGrid();
        board[1][2] = new MineTile(1,2);
        board[1][0] = new MineTile(1,0);
        board[2][0] = new NumberTile(2,0,0);
        grid.fillInRestOfSpaces(board);
        Tile[][] updatedBoard = grid.getGrid();
        Assertions.assertEquals('0', updatedBoard[2][0].printVal());
        Assertions.assertEquals('1', updatedBoard[1][0].printVal());
        Assertions.assertEquals('1', updatedBoard[2][1].printVal());


    }




}

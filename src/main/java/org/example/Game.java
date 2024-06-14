package org.example;

import java.util.ArrayList;
/*
public class Game {


    private Board board;
    private Position pos;
    private int startX;
    private int startY;
    private int numOfRows;
    private int numOfCols;

    public Game (int startX, int startY, int numOfRows, int numOfCols) {
        this.pos = new Position(startX, startY);
        this.startX = startX;
        this.startY = startY;
        this.board = new Board(numOfRows, numOfCols);

    }


    public Tile[][] createBasicGrid(){
        Board grid = this.getBoard();
        for (int i= 0; i < board.getNumOfRows(); i++) {
            for (int j=0; j < board.getNumOfCols(); j++) {
                grid[i][j] = new Tile(i,j);
            }
        }
        grid[startX][startY] = new EmptyTile(startX, startY);
        int[] numList1 = {-1, 1, 2,-2};
        ArrayList<Position> noMineList = generateCoords(startX, startY, numList1);
        grid = noMineZone(grid, noMineList);
        this.setGrid(grid);
        ArrayList<Position> freePositions = getFreeSpaces(noMineList, grid);
        placeMines(freePositions, grid);
        fillRestOfBoard(grid);

        return grid;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

}
*/
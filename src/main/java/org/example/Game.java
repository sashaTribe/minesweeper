package org.example;

import java.util.ArrayList;
import java.util.Scanner;
public class Game {

    private NewGrid grid;
    private boolean hasGameStarted;
    private Position pos;
    private boolean hasMineRevealed;
    private int numOfFlags;

    public Game(int numOfRows, int numOfCols) {
        this.hasGameStarted = true;
        this.hasMineRevealed = false;
        this.grid = new NewGrid(numOfRows, numOfCols);
        //this.numOfFlags = this.grid.getNumOfMines();

    }

    public void startGame() {
        NewGrid board = this.getGrid();
        this.setNumOfFlags(board.getNumOfMines());
        board.createGrid();
        while (!this.isHasMineRevealed()) {
            System.out.println("You have " + this.getNumOfFlags() + " left.");
            runGame();
        }
        System.out.println("Game has ended.");


    }


    public void runGame () {
        System.out.println("Would you like to place flag or reveal tile");
        Scanner scn = new Scanner(System.in);
        String instruction = scn.nextLine();
        Tile[][] board = grid.getGrid();
        this.grid.printGrid(board);
        System.out.println("Can you give me the x coordinate: ");
        int xpos = scn.nextInt() - 1;
        System.out.println("Can you give me the y coordinate: ");
        int ypos = scn.nextInt() - 1;

        if (instruction.equals("Place Flag")) {
            placeFlag(board,xpos, ypos );
        } else if (instruction.equals("Reveal Tile")) {
            revealTile(xpos, ypos);
        } else {
            System.out.println("Instruction invalid");
        }
        //Grid grid = this.getGrid();
        this.getGrid().printGrid(this.getGrid().getGrid());

    }

    public void placeFlag (Tile[][] board, int x, int y){
        //Tile[][] current_grid = grid.getGrid();
        if (board[x][y].isHidden()) {
            board[x][y].setHidden(false);
            board[x][y].setFlagged(true);
            this.setNumOfFlags(this.getNumOfFlags() - 1);
        }
        this.getGrid().setGrid(board);
    }

    public void revealTile ( int x, int y){
        Tile[][] grid = this.getGrid().getGrid();
        System.out.println("hello");
        if (grid[x][y].isHidden()) {
            grid[x][y].setHidden(false);
            System.out.println("Tile no longer hidden");
            //this.getGrid().printGrid(grid);
            if (grid[x][y] instanceof MineTile) {
                System.out.println("Mine set off, you lost!");
                this.setHasMineRevealed(true);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i][j].setHidden(false);
                        System.out.print(grid[i][j].printVal());
                        System.out.print(' ');
                    }
                    System.out.println();
                }
            }
            /*
            if (grid[x][y].printVal() == '0') {
                grid.dfsReveal(x,y, grid);
            }

             */
        } else {
            System.out.println("This is already revealed, choose another");
        }
        this.getGrid().setGrid(grid);

    }

    public Position getPos () {
        return pos;
    }

    public void setPos (Position pos){
        this.pos = pos;
    }

    public int getNumOfFlags () {
        return numOfFlags;
    }

    public void setNumOfFlags ( int numOfFlags){
        this.numOfFlags = numOfFlags;
    }

    public NewGrid getGrid () {
        return grid;
    }

    public void setGrid (NewGrid grid){
        this.grid = grid;
    }
    public boolean isHasMineRevealed() {
        return hasMineRevealed;
    }

    public void setHasMineRevealed(boolean hasMineRevealed) {
        this.hasMineRevealed = hasMineRevealed;
    }


    }


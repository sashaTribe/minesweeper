package org.example;

import java.util.ArrayList;
import java.util.Scanner;
public class Game {

    private Grid grid;
    private boolean hasGameStarted;
    private Position pos;
    private boolean hasMineRevealed;
    private int numOfFlags = 10;

    public Game(boolean hasGameStarted) {
        this.hasGameStarted = hasGameStarted;
        this.hasMineRevealed = false;
    }

    public void startGame() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print('H');
            }
            System.out.print('\n');
        }
        Scanner scn = new Scanner(System.in);
        System.out.println("X Starting point: ");
        int startX = scn.nextInt();
        System.out.println("Y Starting point: ");
        int startY = scn.nextInt();
        Grid grid = new Grid(startX, startY);

        Tile[][] showGrid = grid.createBasicGrid();

        grid.setGrid(showGrid);
        printGrid(showGrid);

        while (!isHasMineRevealed()) {
            runGame();

        }
    }

    public void printGrid(Tile[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isHidden()) {
                    System.out.print(grid[i][j].printIsHidden());
                } else {
                    System.out.print(grid[i][j].printVal() + " ");
                }
            }
            System.out.println();
        }
    }

        public void runGame () {
            System.out.println("Would you like to place flag or reveal tile");
            Scanner scn = new Scanner(System.in);
            String instruction = scn.nextLine();
            Grid grid = this.getGrid();
            Tile[][] board = grid.getGrid();
            printGrid(board);
            System.out.println("Can you give me the x coordinate: ");
            int xpos = scn.nextInt() - 1;
            System.out.println("Can you give me the y coordinate: ");
            int ypos = scn.nextInt() - 1;

            if (instruction.equals("Place Flag")) {
                placeFlag(grid,xpos, ypos );
            } else if (instruction.equals("Reveal Tile")) {
                revealTile(xpos - 1, ypos -1);
            } else {
                System.out.println("Instruction invalid");
            }
            System.out.println("I have reached this place");
            //Grid grid = this.getGrid();
            printGrid(grid.getGrid());

        }

        public void placeFlag ( Grid grid, int x, int y){
            Tile[][] current_grid = grid.getGrid();
            if (current_grid[x][y].isHidden()) {
                current_grid[x][y].setHidden(false);
                current_grid[x][y].setFlagged(true);
                this.setNumOfFlags(this.getNumOfFlags() - 1);
            }
            this.getGrid().setGrid(current_grid);
        }

        public void revealTile ( int x, int y){
            Tile[][] grid = this.getGrid().getGrid();
            if (grid[x][y].isHidden()) {
                grid[x][y].setHidden(false);
                System.out.println("Tile no longer hidden");
                if (grid[x][y] instanceof MineTile) {
                    System.out.println("Mine set off, you lost!");
                }
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

        public Grid getGrid () {
            return grid;
        }

        public void setGrid (Grid grid){
            this.grid = grid;
        }
    public boolean isHasMineRevealed() {
        return hasMineRevealed;
    }

    public void setHasMineRevealed(boolean hasMineRevealed) {
        this.hasMineRevealed = hasMineRevealed;
    }


    }


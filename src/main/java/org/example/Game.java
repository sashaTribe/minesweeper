package org.example;

import java.util.ArrayList;
import java.util.Scanner;
public class Game {




    private Grid grid;
    private boolean hasGameStarted;
    private Position pos;
    private int numOfFlags = 10;

    public Game (boolean hasGameStarted) {
        this.hasGameStarted = hasGameStarted;
    }

    public void startGame(){

        for (int i=0; i< 9; i++) {
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
        Tile [][] showGrid = grid.createBasicGrid();
        for (int i=0; i< showGrid.length; i++) {
            for(int j=0; j<showGrid[i].length; j++) {
                if(showGrid[i][j].isHidden()) {
                    System.out.print(showGrid[i][j].printIsHidden());
                } else {
                    System.out.print(showGrid[i][j].printVal() + " ");
                }
            }
            System.out.println();
        }
        grid.createBasicGrid();
        for (int i = 0; i < grid.getGrid().length; i++) {
            for (int j = 0; j < grid.getGrid()[i].length; j++) {
                if (grid.getGrid()[i][j].isHidden()) {
                    System.out.print(grid.getGrid()[i][j].printIsHidden());
                } else {
                    System.out.print(grid.getGrid()[i][j].printVal() + " ");
                }
            }
            System.out.println();
        }

    }

    public void runGame(){
        System.out.println("Would you like to place flag or reveal tile");
        Scanner scn = new Scanner(System.in);
        String instruction = scn.next();

        System.out.println("Can you give me the x coordinate: ");
        int xpos = scn.nextInt();
        System.out.println("Can you give me the y coordinate: ");
        int ypos = scn.nextInt();

        if (instruction.equals("Place Flag")){
            // call a method
        }else if (instruction.equals("Reveal Tile")){
            //call a method
        }else {
            System.out.println("Instruction invalid");
        }
    }

    public void placeFlag(int x, int y){
        Tile[][] grid = this.getGrid().getGrid();
        if (grid[x][y].isHidden()) {
            grid[x][y].setHidden(false);
            grid[x][y].setFlagged(true);
        }
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public int getNumOfFlags() {
        return numOfFlags;
    }

    public void setNumOfFlags(int numOfFlags) {
        this.numOfFlags = numOfFlags;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }



}



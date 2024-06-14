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


}



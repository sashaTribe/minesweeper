package org.example;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("How many rows for the grid: ");
        int numOfRows = scn.nextInt();
        System.out.println("How many columns for the grid: ");
        int numOfColumns = scn.nextInt();
        Game game = new Game(numOfRows,numOfColumns);
        game.startGame();

         /*
        NewGrid grid = new NewGrid(6,6);
        grid.createGrid();
          */
        /*
        Scanner scn = new Scanner(System.in);
        System.out.println("X Starting point: ");
        int startX = scn.nextInt();
        System.out.println("Y Starting point: ");
        int startY = scn.nextInt();
        Grid gameGrid = new Grid(startX - 1, startY - 1);
        Tile[][] board = gameGrid.createBasicGrid();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isHidden()) {
                    System.out.print(board[i][j].printIsHidden());
                } else {
                    System.out.print(board[i][j].printVal() + " ");
                }
            }
            System.out.println();
        }

         */
    }



}
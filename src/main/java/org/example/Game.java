package org.example;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.Scanner;
public class Game {

    private NewGrid grid;
    private boolean hasGameStarted;
    private Position pos;
    private boolean hasMineRevealed;
    private boolean hasAllTilesBeenRevealed;
    private int numOfFlags;

    public Game(int numOfRows, int numOfCols) {
        this.hasGameStarted = true;
        this.hasMineRevealed = false;
        this.hasAllTilesBeenRevealed = false;
        this.grid = new NewGrid(numOfRows, numOfCols);
        //this.numOfFlags = this.grid.getNumOfMines();

    }

    public void startGame() {
        NewGrid grid = this.getGrid();
        grid.createGrid();
        Tile[][] board = grid.getGrid();
        this.setNumOfFlags(grid.getNumOfMines());
        while (!this.isHasMineRevealed() || !(this.areAllTilesRevealed(board))){
            System.out.println("You have " + this.getNumOfFlags() + " flags left.");
            runGame();
        }
        System.out.println("Game has ended.");


    }


    public void runGame() {
        Tile[][] board = grid.getGrid();
        if (this.areAllTilesRevealed(board)){
            System.out.println("You have won the game!");
            this.setHasAllTilesBeenRevealed(true);
        }
        this.grid.printGrid(board);
        System.out.println("Would you like to: ");
        System.out.println("1. Place Flag ");
        System.out.println("2. Reveal Tile");
        System.out.println("3. Remove Flag");
        System.out.println("Type in the number of the instruction");
        Scanner scn = new Scanner(System.in);
        int instruction = scn.nextInt();

        this.grid.printGrid(board);

        if (instruction == 1) {
            placeFlag(board);
        } else if (instruction == 2) {
            revealTile();
        } else if(instruction == 3) {
            removeFlag(board);
        }else {
            System.out.println("Instruction invalid");
        }

    }

    public void placeFlag(Tile[][] board) {
        Position pos = getPositionPrompt(board);
        int x = pos.getPosX();
        int y = pos.getPosY();
        if(isInputValid(x, board.length) && isInputValid(y,board[0].length)){
            if (board[x][y].isHidden()) {
                board[x][y].setHidden(false);
                board[x][y].setFlagged(true);
                this.setNumOfFlags(this.getNumOfFlags() - 1);
            }
            this.getGrid().setGrid(board);
        } else{
            System.out.println("Value(s) you have typed invalid.");
        }

    }

    public Position getPositionPrompt(Tile[][] board) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Can you give me the row number: ");
        int x = scn.nextInt() - 1;
        System.out.println("Can you give me the column number: ");
        int y = scn.nextInt() - 1;
        Position pos = new Position(x,y);
        return pos;
    }

    public static boolean isInputValid(int val, int size) {
        return (val >= 0 && val < size);
    }

    public void revealTile() {
        Tile[][] grid = this.getGrid().getGrid();
        Position pos = getPositionPrompt(grid);
        int x = pos.getPosX();
        int y = pos.getPosY();
        if (grid[x][y].isHidden()) {
            grid[x][y].setHidden(false);
            System.out.println("Tile no longer hidden");
            if (grid[x][y] instanceof MineTile) {
                System.out.println("Mine set off, you lost!");
                this.getGrid().revealTheWholeBoard(grid);
                this.setHasAllTilesBeenRevealed(true);
                this.setHasMineRevealed(true);

            }
        } else {
            System.out.println("This is already revealed, choose another");
        }
        this.getGrid().setGrid(grid);

    }



    public static boolean areAllTilesRevealed (Tile[][] board) {
        int counter = 0;
        for (int i=0; i < board.length; i++) {
            for (int j=0; j < board[0].length; j++){
                if (!board[i][j].isHidden()) {
                    counter += 1;
                }
            }
        }
        return (counter == (board[0].length * board.length));
    }


    private void removeFlag(Tile[][] board) {
        ArrayList<Position> flagPositions = this.getFlaggedCoordinates(board);
        System.out.println();
        if (!flagPositions.isEmpty()){
            int counter = 0;
            for (Position pos : flagPositions) {
                System.out.println(counter + "." + (pos.getPosX() + 1) + "," + (pos.getPosY()+1));
                counter += 1;
            }
            System.out.println(" Select the number of the selection: ");
            try {
                Scanner scn = new Scanner(System.in);
                int userChoice = scn.nextInt();
                Position chosenPosition = flagPositions.get(userChoice);
                board[chosenPosition.getPosX()][chosenPosition.getPosY()].setFlagged(false);
                board[chosenPosition.getPosX()][chosenPosition.getPosY()].setHidden(true);
                int numOfFlags = this.getNumOfFlags();
                this.setNumOfFlags(numOfFlags + 1);
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
            this.grid.setGrid(board);
        } else {
            System.out.println("You have not placed any flags");
        }

    }

        private ArrayList<Position> getFlaggedCoordinates (Tile[][]board){
            ArrayList<Position> positions = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j].isFlagged()) {
                        Position newPos = new Position(i, j);
                        positions.add(newPos);
                    }
                }
            }
            return positions;
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
        public boolean isHasMineRevealed () {
            return hasMineRevealed;
        }

        public void setHasMineRevealed (boolean hasMineRevealed){
            this.hasMineRevealed = hasMineRevealed;
        }

    public boolean isHasAllTilesBeenRevealed() {
        return hasAllTilesBeenRevealed;
    }

    public void setHasAllTilesBeenRevealed(boolean hasAllTilesBeenRevealed) {
        this.hasAllTilesBeenRevealed = hasAllTilesBeenRevealed;
    }


    }




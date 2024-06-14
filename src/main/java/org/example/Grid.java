package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Grid {


    // private String difficulty;
    private int numOfMines;
    private int numOfCols;
    private int numOfRows;
    private Tile[][] grid;
    private int startX;
    private int startY;
    private Position position;

    public Grid(int startX, int startY){
        this.startX = startX;
        this.startY = startY;
        this.position = new Position(startX, startY);
        this.numOfCols = 9;
        this.numOfRows = 9;
        this.numOfMines = 10;
        this.grid = new Tile[numOfRows][numOfCols];
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public int getNumOfCols() {
        return numOfCols;
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }


    public Tile[][] getGrid() {
        return grid;
    }

    private boolean goesOverMax (int changedX, int changedY) {
        int maxX = this.numOfCols -1;
        return (changedX > maxX || changedY > maxX);
    }
    private boolean goesbelowMin (int changedX, int changedY) {
        int minVal = this.numOfCols -1;
        return (changedX < minVal || changedY < minVal);
    }

    public Tile[][] createBasicGrid(){
        Tile[][] grid = this.getGrid();
        int startX = this.getStartX();
        int startY = this.getStartY();
        for (int i= 0; i < numOfRows; i++) {
            for (int j=0; j < numOfCols; j++) {
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

    private Tile[][] noMineZone(Tile[][] grid, ArrayList<Position> coordsList) {
        for (Position position : coordsList) {
            int tempX = position.getPosX();
            int tempY = position.getPosY();
            grid[tempX][tempY] = new EmptyTile(tempX, tempY);
        }
        return grid;
    }

    public ArrayList<Position> generateCoords(int x, int y, int[] num) {
        System.out.println("It is doing ok in generate coords before I initiate list");
        ArrayList<Position> coordsList = new ArrayList<>();
        for (int i=0; i<num.length; i++) {
            coordsList.add(generateCoordFromX(x, y, num[i]));
            coordsList.add(generateCoordFromY(x, y, num[i]));
            coordsList.add(generateCoordFromXY(x,y,num[i]));

        }
        //System.out.println("Length of my coords list: " + coordsList.size());
        for (Position position: coordsList ) {
            System.out.println(position.getPosX() + " " + position.getPosY());
            if ((position.getPosX() == x) && (position.getPosY() == y)) {
                coordsList.remove(position);
                //System.out.println("Achieved");
            }
        }
        //System.out.println("I have successfully finalised the lists");
        return coordsList;
    }

    private Position generateCoordFromX(int x, int y, int num) {
        int newX = x + num;
        Position position = new Position(x, y);
        if (!(goesOverMax(newX, y)) || !(goesbelowMin(newX,y))) {
            position.setPosX(newX);
        }
        return position;
    }

    private Position generateCoordFromY(int x, int y, int num) {
        int newY = y + num;
        Position position = new Position(x, y);
        if (!(goesOverMax(x, newY)) || !(goesbelowMin(x, newY))) {
            position.setPosY(newY);
        }
        return position;
    }

    private Position generateCoordFromXY(int x, int y, int num) {
        int newY = y + num;
        int newX = x +num;
        Position position = new Position(x, y);
        if (!(goesOverMax(newX, newY)) || !(goesbelowMin(newX, newY))) {
            position.setPosY(newY);
        }
        return position;
    }

    private ArrayList<Position> getFreeSpaces(ArrayList<Position> positions, Tile[][] grid) {
        ArrayList<Position> freeSpaces = new ArrayList<Position>();
        for (int i=0; i< grid.length; i++) {
            for (int j=0; j < grid[i].length; j++) {
                Position tempPos = new Position(i,j);
                if (!positions.contains(tempPos)) {
                    freeSpaces.add(tempPos);
                }

            }
        }
        return freeSpaces;
    }

    private ArrayList<Integer> generateRandomNumbers (int min, int max, int size) {
        Random random = new Random();
        ArrayList<Integer> indexVals = new ArrayList<Integer>();
        int i = 0;
        while (size > 0) {
            int randomValue = random.nextInt(max-min) + min;
            if (!indexVals.contains(randomValue)){
                indexVals.add(randomValue);
                size -= 1;
            }
        }
        return indexVals;

    }

    private ArrayList<Position> getMinePositions (ArrayList<Integer> indexList, ArrayList<Position> positionList) {
        ArrayList<Position> chosenPositions = new ArrayList<>();
        for(int num : indexList) {
            chosenPositions.add(positionList.get(num));
        }
        return chosenPositions;
    }

    private void placeMines(ArrayList<Position> positions, Tile[][] grid){
        // generate a random number list
        //System.out.print(positions.size());
        ArrayList<Integer> numList = generateRandomNumbers(0, positions.size(),10);
        ArrayList<Position> minePositions = getMinePositions(numList, positions);

        for (Position position : minePositions) {
            int tempX = position.getPosX();
            int tempY = position.getPosY();
            grid[tempX][tempY] = new MineTile(tempX,tempY);
        }
        this.setGrid(grid);
    }

    private void fillRestOfBoard (Tile[][] grid){
        for (int i=0; i < grid.length; i++){
            for (int j=0; j < grid[i].length; j++) {
                if (!(grid[i][j] instanceof MineTile)) {
                    //System.out.println("It is happy noticing that that is not a Mine");
                    Position position = new Position(i,j);
                    int numOfMines = calcNumMines(position, grid);
                    //System.out.println("It is happy to calculate the amount of surrounding mines");
                    grid[i][j] = new NumberTile(i,j,numOfMines);
                }
            }
        }
        this.setGrid(grid);
    }

    private int calcNumMines (Position position, Tile[][] grid) {
        int[] numList = {1, -1, 0};
        System.out.println("It is happy before creating that list");
        //ArrayList<Position> coords = generateCoords(position.getPosX(), position.getPosY(), numList);
        System.out.println("It is happy to create coordinates list");
        int count = 0;
        /*
        for (int i=0; i < coords.size(); i++) {
            int x = position.getPosX();
            int y = position.getPosY();
            if (grid[x][y] instanceof MineTile) {
                count += 1;
            }
        }

         */
        int x = position.getPosX();
        int y = position.getPosY();
        for (int i = 0; i < numList.length; i++) {

            int tempValX = x + numList[i];
            int tempValY = y + numList[i];
            if ((tempValY >= 0 && tempValY < 9) && (tempValX >= 0 && tempValX < 9)) {
                if ((grid[tempValX][y] instanceof MineTile)) {
                    count += 1;
                    System.out.println("Yes");
                }
                if ((grid[x][tempValY] instanceof MineTile)) {
                    count += 1;
                    System.out.println(count);
                }
                if ((grid[tempValX][tempValY] instanceof MineTile)){
                    count += 1;
                    System.out.println(count);
                }
            }

        }
        return count;
    }

    public void updateGrid(Tile[][] grid, int x, int y) {

    }




}

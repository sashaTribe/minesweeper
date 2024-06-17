package org.example;
import java.util.*;

public class NewGrid {
    private int numOfMines;
    private final int numOfCols;
    private final int numOfRows;
    private Tile[][] grid;
    private int startX;
    private int startY;
    private ArrayList<Integer[]> visitedPositions = new ArrayList<>();


    public NewGrid (int numOfCols, int numOfRows){
        this.numOfCols = numOfCols;
        this.numOfRows = numOfRows;
        this.grid = new Tile[numOfRows][numOfCols];
    }

    public void createGrid(){
        Tile[][] board = this.getGrid();
        board = initiateBoard(board);
        this.setGrid(board);
        Scanner scn = new Scanner(System.in);
        this.calculateNumOfMines();
        printGrid(board);
        Position pos = getValidatedPosition(board);
        int startX = pos.getPosX();
        int startY = pos.getPosY();

        setUpStartingPoint(startX, startY);
        this.setStartX(startX);
        this.setStartY(startY);

        noZeroZone(startX,startY,1,board);

        ArrayList<Position> freePositions = getFreeSpaces(board);
        this.placeMines(freePositions, board, numOfMines);
        //System.out.println("I have placed the mines");
        this.fillInRestOfSpaces(board);
        board = this.getGrid();
        this.dfsReveal(startX, startY, board);
        //System.out.println("I have filled out the rest of my board");
        board = this.getGrid();
        countMineForStart(startX,startY, board);
        this.printGrid(board);

    }

    public Position getValidatedPosition (Tile[][] board) {
        boolean isValidInput = false;
        int startX = 0;
        int startY = 0;
        Scanner scn = new Scanner(System.in);
        do {
            try {
                System.out.println("Starting x-coordinate: ");
                startX = scn.nextInt() - 1;
                System.out.println("Starting y-coordinate: ");
                startY = scn.nextInt() - 1;
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("The value you entered is not a valid integer, please try again");
                scn.next();
            }
        }while (!isValidInput || !isInputValid(startX, board.length) || !isInputValid(startY, board.length));
        Position pos = new Position(startX, startY);
        return pos;
    }
    public static boolean isInputValid(int val, int size) {
        return (val >= 0 && val < size);
    }
    private void countMineForStart (int startX, int startY, Tile[][] board) {
        int[][] directions = {{0,1},{0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1},{-1,-1}};
        int count = 0;
        for (int[] direction : directions) {
            if(this.isThereAMine(board,direction,startX,startY)){
                count += 1;
            }
        }
        board[startX][startY] = new NumberTile(startX, startY, count);
        board[startX][startY].setHidden(false);
        this.setGrid(board);

    }

    private Tile[][] initiateBoard (Tile[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Tile(i,j);
            }
        }
        return board;
    }


    public void printGrid(Tile[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].printVal());
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public void revealTheWholeBoard (Tile[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setHidden(false);
                board[i][j].setFlagged(false);
                System.out.print(grid[i][j].printVal());
                System.out.print(' ');
            }
            System.out.println();
        }
        this.setGrid(board);
    }
    private void setUpStartingPoint(int x, int y) {
        Tile[][] grid = this.getGrid();
        grid[x][y] = new NumberTile(x,y,0);
        grid[x][y].setHidden(false);
        this.setGrid(grid);
    }

    // bfs search
    private void noZeroZone(int startX, int startY, int islandSize, Tile[][] board) {
        Queue<Position> queue = new LinkedList<>();
        ArrayList<Position> zeroCells = new ArrayList<>();
        Position startPos = new Position(startX,startY);
        int[][] directions = {{0,1},{0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1},{-1,-1}};
        queue.add(startPos);

        while (zeroCells.size() < islandSize) {
            Position tempPos = queue.poll();
            if (!zeroCells.contains(tempPos)) {
                zeroCells.add(tempPos);
                for (int[] direction : directions) {
                    int tempX = tempPos.getPosX() + direction[0];
                    int tempY = tempPos.getPosY() + direction[1];
                    Position newPos = new Position(tempX,tempY);
                    if ((tempX >= 0 && tempX < board.length) && (tempY >= 0 && tempY < board[0].length) && !(zeroCells.contains(newPos))) {
                        queue.add(newPos);
                    }
                }
            }
        }
        System.out.println("Size of zerocells list: " + zeroCells.size());
        for (Position pos : zeroCells) {
            board[pos.getPosX()][pos.getPosY()] = new NumberTile(pos.getPosX(), pos.getPosY(), 0);
            board[pos.getPosX()][pos.getPosY()].setHidden(false);
        }
        System.out.println("Have created non-zero zone");
        this.setGrid(board);
        //this.printGrid(board);

    }

    //
    private ArrayList<Integer> generateRandomNumbers (int min, int max, int size) {
        Random random = new Random();
        System.out.println(min);
        System.out.println(max);
        ArrayList<Integer> indexVals = new ArrayList<Integer>();
        while (size > 0) {
            int randomValue = random.nextInt(max-min) + min;
            if (!indexVals.contains(randomValue)){
                indexVals.add(randomValue);
                size -= 1;

            }

        }
        System.out.println(indexVals);
        return indexVals;

    }

    private ArrayList<Position> getMinePositions (ArrayList<Integer> indexList, ArrayList<Position> positionList) {
        ArrayList<Position> chosenPositions = new ArrayList<>();
        for(int num : indexList) {
            chosenPositions.add(positionList.get(num));
        }
        return chosenPositions;
    }

    private void placeMines(ArrayList<Position> positions, Tile[][] grid, int mineAmount){
        // generate a random number list

        ArrayList<Integer> numList = generateRandomNumbers(0, positions.size(), mineAmount);
        System.out.print("I have generated my numbers");
        ArrayList<Position> minePositions = getMinePositions(numList, positions);


        for (Position position : minePositions) {
            int tempX = position.getPosX();
            int tempY = position.getPosY();
            grid[tempX][tempY] = new MineTile(tempX,tempY);
            //grid[tempX][tempY].setHidden(false);
        }
        this.setGrid(grid);
        //this.printGrid(grid);
    }

    private ArrayList<Position> getFreeSpaces(Tile[][] grid) {
        ArrayList<Position> freeSpaces = new ArrayList<Position>();
        for (int i=0; i< grid.length; i++) {
            for (int j=0; j < grid[i].length; j++) {
                if (!(grid[i][j] instanceof NumberTile) && !(grid[i][j] instanceof MineTile)){
                    Position newPos = new Position(i,j);
                    freeSpaces.add(newPos);
                    }

                }

            }
        return freeSpaces;
    }

    public void fillInRestOfSpaces(Tile[][] board) {
        int[][] directions = {{0,1},{0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1},{-1,-1}};
        for(int i=0; i< board.length; i++) {
            for(int j=0; j < board[0].length; j++) {
                if (!(board[i][j] instanceof  MineTile) && !(board[i][j] instanceof  NumberTile)) {
                    int count = 0;
                    for (int[] direction : directions) {
                        int tempX = i + direction[0];
                        int tempY = j + direction[1];
                        if ((tempX >= 0 && tempX < board.length) && (tempY >=0 && tempY < board[0].length)){
                            if (this.isThereAMine(board, direction, i, j)){
                                count += 1;
                            }
                        }
                    }
                    board[i][j] = new NumberTile(i, j, count);
                    //board[i][j].setHidden(false);

                }

            }
        }
        //System.out.println("I have filled in rest of spaces");
        this.setGrid(board);
        //this.printGrid(board);
    }

    public boolean isThereAMine(Tile[][] board, int[] direction, int x, int y) {
        int xVal = x + direction[0];
        int yVal = y + direction[1];
        if (xVal < 0 || xVal >= board.length || yVal < 0 || yVal >= board[0].length){
            return false;
        }
        return board[xVal][yVal] instanceof MineTile;
    }




    public void dfsReveal(int x, int y, Tile[][] board) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        System.out.println(x + "and" + y);
        ArrayList<Integer[]> visitedPos = this.getVisitedPositions();
        Integer[] tempPos = new Integer[2];
        tempPos[0] = x;
        tempPos[1] = y;
        if (x < 0 || x >= board.length || y >= board[0].length || y < 0 ){
            return;
        }
        if (!(visitedPos.contains(tempPos))) {
            try {
                if (board[x][y] instanceof MineTile){
                    System.out.println("X Val: " + x + "Y Val: " + y);
                    return;
                }

            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("The index taken is out of range");
            }


        }
        board[x][y].setHidden(false);
        visitedPos.add(tempPos);
        this.setVisitedPositions(visitedPos);


        if(((NumberTile) board[x][y]).getNumOfMines() == 0) {

            dfsReveal(x + directions[0][0], y + directions[0][1], board);
            dfsReveal(x + directions[1][0], y + directions[1][1], board);
            dfsReveal(x + directions[2][0], y + directions[2][1], board);
            dfsReveal(x + directions[3][0], y + directions[3][1], board);


        }
    }

    private void calculateNumOfMines(){
        double doubleNumOfMines = Math.ceil(0.2 * this.getNumOfCols() * this.getNumOfRows());
        int numOfMines = (int) doubleNumOfMines;
        this.setNumMines(numOfMines);
    }




        public Tile[][] getGrid () {
            return grid;
        }

        public void setGrid (Tile[][]grid){
            this.grid = grid;
        }

        public int getNumOfCols () {
            return numOfCols;
        }

        public int getNumOfRows () {
            return numOfRows;
        }

        public void setNumMines ( int val){
            this.numOfMines = val;
        }

        public int getNumOfMines () {
            return this.numOfMines;
        }

        public int getStartX () {
            return startX;
        }

        public void setStartX ( int startX){
            this.startX = startX;
        }

        public int getStartY () {
            return startY;
        }

        public void setStartY ( int startY){
            this.startY = startY;
        }

    public ArrayList<Integer[]> getVisitedPositions() {
        return visitedPositions;
    }

    public void setVisitedPositions(ArrayList<Integer[]> visitedPositions) {
        this.visitedPositions = visitedPositions;
    }


}

package org.example;

public class NumberTile extends Tile{

    private int numOfMines;



    public NumberTile(int x, int y, int numOfMines) {
        super(x, y);
        this.numOfMines = numOfMines;
    }


    //private int calcNumOfMines(){}
    public void setNumOfMines(int numOfMines) {
        this.numOfMines = numOfMines;
    }
    public int getNumOfMines() {
        return numOfMines;
    }

    public char printVal(){
        char charReturn;
        int mineValue = this.getNumOfMines();
        switch (mineValue) {
            case 1:
                charReturn = '1';
                break;
            case 2:
                charReturn =  '2';
                break;
            case 3:
                charReturn = '3';
                break;
            case 4:
                charReturn = '4';
                break;
            case 5:
                charReturn = '5';
                break;
            case 6:
                charReturn = '6';
                break;
            case 7:
                charReturn = '7';
                break;
            case 8:
                charReturn = '8';
                break;
            default:
                //System.out.println("Error with mine value");
                charReturn = '0';
                break;
        }
        return charReturn;
    }
}

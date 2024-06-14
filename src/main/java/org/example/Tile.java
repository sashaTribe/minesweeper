package org.example;

public class Tile {
    private Position position;
    //private int x;
    //private int y;
    private boolean isHidden;
    private boolean isFlagged;

    public Tile(int x, int y){
        this.position = new Position(x,y);
        this.isHidden = true;
        this.isFlagged = false;
    }

    public Position getPosition() {
        return this.position;
    }


    public char printVal(){
        if (this.isHidden) {
            return 'H';
        }
        else {
            return '-';
        }
    }
}
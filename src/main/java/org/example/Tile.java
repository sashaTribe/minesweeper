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


    public String printIsHidden(){
        return "H ";
    }
    public char printVal(){
        return '0';
    }
    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }


}

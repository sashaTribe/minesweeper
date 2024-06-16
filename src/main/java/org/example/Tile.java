package org.example;

public class Tile {
    private Position position;
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
        if (isHidden()){
            return 'H';
        } else {
            return '0';
        }

    }
    public boolean isHidden() {
        return this.isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }


}

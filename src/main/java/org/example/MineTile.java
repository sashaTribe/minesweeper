package org.example;

public class MineTile extends Tile{

    public MineTile(int x, int y) {
        super(x,y);
    }

    // Prints M to indicate it is a mine when tile is revealed
    public char printVal() {
        if (this.isHidden()){
            return 'H';
        }else if (this.isFlagged()) {
            return 'F';
        } else {
            return 'M';
        }

    }
}

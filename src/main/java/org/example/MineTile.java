package org.example;

public class MineTile extends Tile{

    public MineTile(int x, int y) {
        super(x,y);
    }

    // Prints M to indicate it is a mine when tile is revealed
    public char printVal() {
        return 'M';
    }
}

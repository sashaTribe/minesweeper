package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class NumberTileTest {
    @Test
    public void printCharPrints2 () {
        final var expected = '2';
        NumberTile tile = new NumberTile(1,2,2);
        tile.setHidden(false);
        Assertions.assertEquals(expected, tile.printVal());
    }

    @Test
    public void printCharPrints3 () {
        final var expected = '3';
        NumberTile tile = new NumberTile(1,2,3);
        tile.setHidden(false);
        Assertions.assertEquals(expected, tile.printVal());
    }

    @Test
    public void printCharPrints4 () {
        final var expected = '4';
        NumberTile tile = new NumberTile(1,2,4);
        tile.setHidden(false);
        Assertions.assertEquals(expected, tile.printVal());
    }
    @Test
    public void printCharPrints1 () {
        final var expected = '1';
        NumberTile tile = new NumberTile(1,2,1);
        tile.setHidden(false);
        Assertions.assertEquals(expected, tile.printVal());
    }
}

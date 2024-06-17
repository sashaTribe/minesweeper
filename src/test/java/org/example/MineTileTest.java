package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MineTileTest {
    @Test
    public void printCharPrintsM () {
        final var expected = 'M';
        MineTile m =new MineTile(3,4);
        Assertions.assertEquals(expected, m.printVal());
    }
}

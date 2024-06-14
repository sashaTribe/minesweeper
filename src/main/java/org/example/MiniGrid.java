package org.example;
import java.util.ArrayList;
public class MiniGrid {
    private int givenX;
    private int givenY;
    private int maxVal;
    private int minVal;

    public MiniGrid (int givenX, int givenY, int maxVal, int minVal){
        this.givenX = givenX;
        this.givenY = givenY;
        this.maxVal = maxVal;
        this.minVal = minVal;
    }



    private boolean goesOverMax (int changedX, int changedY) {
        int maxX = this.maxVal;
        return (changedX > maxX || changedY > maxX);
    }
    private boolean goesbelowMin (int changedX, int changedY) {
        int minVal = this.minVal;
        return (changedX < minVal || changedY < minVal);
    }

}

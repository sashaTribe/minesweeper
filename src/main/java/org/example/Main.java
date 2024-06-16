package org.example;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("How many rows for the grid: ");
        int numOfRows = scn.nextInt();
        System.out.println("How many columns for the grid: ");
        int numOfColumns = scn.nextInt();
        Game game = new Game(numOfRows,numOfColumns);
        game.startGame();


    }



}
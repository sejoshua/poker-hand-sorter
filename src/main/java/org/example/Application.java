package org.example;

import org.example.model.Game;
import org.example.sorter.HandValueDecider;
import org.example.sorter.HandSorter;
import org.example.sorter.impl.SimpleHandValueDecider;
import org.example.sorter.impl.SimpleHandSorter;

import java.util.Scanner;

/**
 * @author Joshua Xing
 */
public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HandSorter sorter = new SimpleHandSorter();
        HandValueDecider decider = new SimpleHandValueDecider();
        Game game = new Game(2, sorter, decider);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            game.addHands(line);
        }
        game.printResult();
    }
}

package org.example.sorter;

import org.example.model.Hand;

/**
 * @author Joshua Xing
 */
public interface HandSorter {
    /**
     * Decides the winner hand between two
     *
     * @param hand1 the 1st hand
     * @param hand2 the 2nd hand
     * @return positive integer if the first hand wins, or negative
     * integer if the second hand wins, or 0 if tie
     */
    int sort(Hand hand1, Hand hand2);
}

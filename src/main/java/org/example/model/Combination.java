package org.example.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Joshua Xing
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Combination {
    HIGH_CARD(1, "Highest value card"),
    PAIR(2, "Two cards of same value"),
    TWO_PAIRS(3, "Two different pairs"),
    THREE_OF_A_KING(4, "Three cards of the same value"),
    STRAIGHT(5, "All five cards in consecutive value order"),
    FLUSH(6, "All five cards having the same suit"),
    FULL_HOUSE(7, "Three of a kind and a Pair"),
    FOUR_OF_A_KIND(8, "Four cards of the same value"),
    STRAIGHT_FLUSH(9, "All five cards in consecutive value order, with the same suit"),
    ROYAL_FLUSH(10, "Ten, Jack, Queen, King and Ace in the same suit");

    private final int rank;
    private final String description;
}

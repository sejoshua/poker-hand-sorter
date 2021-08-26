package org.example.model;

import lombok.Getter;

import java.util.List;

/**
 * @author Joshua Xing
 */
@Getter
public class Hand {
    private final List<Card> cards;
    private final HandValue value;

    public Hand(List<Card> cards, HandValue value) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Expecting 5 cards for a hand.");
        }
        this.cards = cards;
        this.value = value;
    }
}

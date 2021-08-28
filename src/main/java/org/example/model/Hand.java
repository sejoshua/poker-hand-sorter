package org.example.model;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Card> uniqueCards = new HashSet<>(cards);
        if (uniqueCards.size() != 5) {
            throw new IllegalArgumentException("There are duplicates in the cards: " + cards);
        }
        this.cards = cards;
        this.value = value;
    }
}

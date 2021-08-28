package org.example.sorter.impl;

import org.example.model.Card;
import org.example.model.Combination;
import org.example.model.HandValue;
import org.example.sorter.HandValueDecider;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Joshua Xing
 */
public class SimpleHandValueDecider implements HandValueDecider {
    @Override
    public HandValue getHandValue(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Expecting 5 cards to determine combination.");
        }
        List<Card> cardsCopy = new ArrayList<>(cards);
        Collections.sort(cardsCopy);

        // value -> occurrences
        Map<Integer, Integer> occurrenceMap = new HashMap<>();
        for (Card card : cards) {
            int occurrences = occurrenceMap.getOrDefault(card.getValue(), 0);
            occurrenceMap.put(card.getValue(), occurrences + 1);
        }
        if (occurrenceMap.containsValue(4)) {
            int primaryValue = occurrenceMap.entrySet().stream()
                    .filter(e -> e.getValue() == 4)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList())
                    .get(0);
            return new HandValue(Combination.FOUR_OF_A_KIND, primaryValue);
        } else if (occurrenceMap.containsValue(3)) {
            int primaryValue = occurrenceMap.entrySet().stream()
                    .filter(e -> e.getValue() == 3)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList())
                    .get(0);
            if (occurrenceMap.containsValue(2)) {
                return new HandValue(Combination.FULL_HOUSE, primaryValue);
            } else {
                return new HandValue(Combination.THREE_OF_A_KIND, primaryValue);
            }
        } else if (occurrenceMap.containsValue(2)) {
            if (occurrenceMap.size() == 3) {
                List<Integer> values = occurrenceMap.entrySet().stream()
                        .filter(e -> e.getValue() == 2)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                int primaryValue = Math.max(values.get(0), values.get(1));
                int secondaryValue = Math.min(values.get(0), values.get(1));
                return new HandValue(Combination.TWO_PAIRS, primaryValue, secondaryValue);
            } else {
                int primaryValue = occurrenceMap.entrySet().stream()
                        .filter(e -> e.getValue() == 2)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())
                        .get(0);
                return new HandValue(Combination.PAIR, primaryValue);
            }
        }

        if (isTheSameSuit(cardsCopy)) {
            if(isStraight(cardsCopy)) {
                if (cardsCopy.get(0).getValue() == 10) {
                    return new HandValue(Combination.ROYAL_FLUSH);
                } else {
                    return new HandValue(Combination.STRAIGHT_FLUSH);
                }
            } else {
                return new HandValue(Combination.FLUSH);
            }
        }

        if (isStraight(cardsCopy)) {
            return new HandValue(Combination.STRAIGHT);
        }

        return new HandValue(Combination.HIGH_CARD);
    }

    /**
     * Checks if all cards belong to the same suit
     *
     * @param cards the cards to be checked
     * @return true if all cards have the same suit, or false otherwise
     */
    public static boolean isTheSameSuit(List<Card> cards) {
        return cards.stream().map(Card::getSuit).collect(Collectors.toSet()).size() == 1;
    }

    /**
     * Checks if the <b>SORTED</b> cards (by values, regardless their suits) are
     * a straight
     *
     * @param cards the cards to be checked
     * @return true if all cards forms a straight, or false otherwise
     */
    public static boolean isStraight(List<Card> cards) {
        for (int i = 1; i < cards.size(); ++i) {
            if (cards.get(i).getValue() - cards.get(i - 1).getValue() != 1) {
                return false;
            }
        }
        return true;
    }
}

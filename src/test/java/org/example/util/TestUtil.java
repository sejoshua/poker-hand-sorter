package org.example.util;

import org.example.model.Card;
import org.example.model.Combination;
import org.example.model.Hand;
import org.example.model.HandValue;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Joshua Xing
 */
public class TestUtil {
    private TestUtil() {
    }

    public static List<Card> from(List<String> cardStrs) {
        if (cardStrs.size() != 5) {
            throw new IllegalArgumentException("Expecting 5 strings, but got: " + cardStrs.size());
        }
        return cardStrs.stream().map(Card::from).collect(Collectors.toList());
    }

    public static Hand from(List<String> cardStrs, Combination combination, int primaryValue, int secondValue) {
        List<Card> cards = from(cardStrs);
        HandValue value = new HandValue(combination, primaryValue, secondValue);
        return new Hand(cards, value);
    }
}

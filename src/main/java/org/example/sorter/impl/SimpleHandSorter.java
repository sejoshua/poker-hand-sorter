package org.example.sorter.impl;

import org.example.model.Card;
import org.example.model.Hand;
import org.example.sorter.HandSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joshua Xing
 */
public class SimpleHandSorter implements HandSorter {
    @Override
    public int sort(Hand hand1, Hand hand2) {
        // if the combinations are not equal, the winner hand can be decided by combination
        if (hand1.getValue().getCombination() != hand2.getValue().getCombination()) {
            return hand1.getValue().getCombination().getRank() - hand2.getValue().getCombination().getRank();
        }
        // now the combinations are the same
        // if coming to the hands with 2/3/4 cards of the same value
        // compare the primary and secondary values to decide
        if (hand1.getValue().getPrimaryValue() != 0) {
            int diff = hand1.getValue().getPrimaryValue() - hand2.getValue().getPrimaryValue();
            if (diff != 0) {
                return diff;
            }
        }
        if (hand1.getValue().getSecondaryValue() != 0) {
            int diff = hand1.getValue().getSecondaryValue() - hand2.getValue().getSecondaryValue();
            if (diff != 0) {
                return diff;
            }
        }
        // now all the cards in each hand are unique
        // sort each hand, and then compare from the largest card in each hand
        // to the smallest one
        List<Card> cardsCopy1 = new ArrayList<>(hand1.getCards());
        List<Card> cardsCopy2 = new ArrayList<>(hand2.getCards());
        Collections.sort(cardsCopy1);
        Collections.sort(cardsCopy2);
        for (int i = cardsCopy1.size() - 1; i >= 0; --i) {
            int diff = cardsCopy1.get(i).getValue() - cardsCopy2.get(i).getValue();
            if (diff != 0) {
                return diff;
            }
        }
        return 0;
    }
}

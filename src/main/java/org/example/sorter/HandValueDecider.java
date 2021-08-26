package org.example.sorter;

import org.example.model.Card;
import org.example.model.HandValue;

import java.util.List;

/**
 * @author Joshua Xing
 */
public interface HandValueDecider {
    HandValue getHandValue(List<Card> cards);
}

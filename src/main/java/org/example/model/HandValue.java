package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Joshua Xing
 */
@AllArgsConstructor
@Getter
public class HandValue {
    private final Combination combination;

    /**
     * {@code primaryValue} and {@code secondaryValue} are used for the
     * hands with cards of the same value.
     * <ul>
     *     <li>{@code primaryValue} is</li>
     *     <ul>
     *         <li>the value of the 4 same cards in "Four of a kind"</li>
     *         <li>the value of the 3 same cards in "Full house" or "Three of a kind"</li>
     *         <li>the value of the bigger pair in "Two pairs"</li>
     *         <li>the value of the pair in "Pair"</li>
     *     </ul>
     *     <li>{@code secondaryValue} is</li>
     *     <ul>
     *         <li>the value of the smaller pair in "Two pairs"</li>
     *     </ul>
     * </ul>
     */
    private final int primaryValue;
    private final int secondaryValue;

    public HandValue(Combination combination, int primaryValue) {
        this(combination, primaryValue, 0);
    }

    public HandValue(Combination combination) {
        this(combination, 0, 0);
    }
}

package org.example.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Joshua Xing
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class Card implements Comparable<Card> {
    private static final Pattern VALID_CARD_PATTERN = Pattern.compile("^([1-9TJKQA])([DHSC])$");

    private final int value;
    private final char suit;

    @Override
    public int compareTo(Card o) {
        return this.value - o.getValue();
    }

    @Override
    public String toString() {
        return String.valueOf(value) + suit;
    }

    public static Card from(String cardStr) {
        Matcher matcher = VALID_CARD_PATTERN.matcher(cardStr);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid card: " + cardStr);
        }
        String valueStr = matcher.group(1);
        String suitStr = matcher.group(2);
        return new Card(valueStringToValue(valueStr), suitStr.charAt(0));
    }

    private static int valueStringToValue(String valueStr) {
        switch (valueStr) {
            case "T":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(valueStr);
        }
    }
}

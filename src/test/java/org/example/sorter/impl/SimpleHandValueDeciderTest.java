package org.example.sorter.impl;

import org.example.model.Card;
import org.example.model.Combination;
import org.example.model.HandValue;
import org.example.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Joshua Xing
 */
class SimpleHandValueDeciderTest {

    @Test
    void getHandValueTest() {
        List<Card> cards1 = TestUtil.from(List.of("AH", "2C", "3H", "4D", "5S"));
        List<Card> cards2 = TestUtil.from(List.of("AH", "2C", "2D", "KS", "9D"));
        List<Card> cards3 = TestUtil.from(List.of("AH", "2C", "2D", "KS", "KD"));
        List<Card> cards4 = TestUtil.from(List.of("AH", "2C", "2D", "2S", "9D"));
        List<Card> cards5 = TestUtil.from(List.of("5H", "8C", "4D", "7S", "6D"));
        List<Card> cards6 = TestUtil.from(List.of("AC", "2C", "3C", "KC", "9C"));
        List<Card> cards7 = TestUtil.from(List.of("AH", "2C", "2D", "AS", "AD"));
        List<Card> cards8 = TestUtil.from(List.of("2H", "2C", "2D", "KS", "2S"));
        List<Card> cards9 = TestUtil.from(List.of("QD", "JD", "9D", "KD", "TD"));
        List<Card> cards10 = TestUtil.from(List.of("AH", "KH", "TH", "QH", "JH"));

        SimpleHandValueDecider decider = new SimpleHandValueDecider();

        assertHandValue(Combination.HIGH_CARD, 0, 0, decider.getHandValue(cards1));
        assertHandValue(Combination.PAIR, 2, 0, decider.getHandValue(cards2));
        assertHandValue(Combination.TWO_PAIRS, 13, 2, decider.getHandValue(cards3));
        assertHandValue(Combination.THREE_OF_A_KIND, 2, 0, decider.getHandValue(cards4));
        assertHandValue(Combination.STRAIGHT, 0, 0, decider.getHandValue(cards5));
        assertHandValue(Combination.FLUSH, 0, 0, decider.getHandValue(cards6));
        assertHandValue(Combination.FULL_HOUSE, 14, 0, decider.getHandValue(cards7));
        assertHandValue(Combination.FOUR_OF_A_KIND, 2, 0, decider.getHandValue(cards8));
        assertHandValue(Combination.STRAIGHT_FLUSH, 0, 0, decider.getHandValue(cards9));
        assertHandValue(Combination.ROYAL_FLUSH, 0, 0, decider.getHandValue(cards10));
    }

    @Test
    void isTheSameSuitTest() {
        List<Card> cards1 = TestUtil.from(List.of("AH", "2H", "5H", "QH", "8H"));
        List<Card> cards2 = TestUtil.from(List.of("KS", "4S", "JS", "8S", "6S"));
        List<Card> cards3 = TestUtil.from(List.of("3C", "7C", "TC", "9C", "JC"));
        List<Card> cards4 = TestUtil.from(List.of("6D", "QD", "9D", "TD", "KD"));

        List<Card> cards5 = TestUtil.from(List.of("6D", "QC", "9D", "TD", "QD"));
        List<Card> cards6 = TestUtil.from(List.of("6H", "QD", "9S", "TD", "QD"));
        List<Card> cards7 = TestUtil.from(List.of("6C", "QS", "9D", "TH", "QD"));
        List<Card> cards8 = TestUtil.from(List.of("6C", "QS", "9C", "TH", "QD"));

        assertTrue(SimpleHandValueDecider.isTheSameSuit(cards1));
        assertTrue(SimpleHandValueDecider.isTheSameSuit(cards2));
        assertTrue(SimpleHandValueDecider.isTheSameSuit(cards3));
        assertTrue(SimpleHandValueDecider.isTheSameSuit(cards4));

        assertFalse(SimpleHandValueDecider.isTheSameSuit(cards5));
        assertFalse(SimpleHandValueDecider.isTheSameSuit(cards6));
        assertFalse(SimpleHandValueDecider.isTheSameSuit(cards7));
        assertFalse(SimpleHandValueDecider.isTheSameSuit(cards8));
    }

    @Test
    void isStraight() {
        List<Card> cards1 = TestUtil.from(List.of("2H", "3H", "4H", "5H", "6H"));
        List<Card> cards2 = TestUtil.from(List.of("7S", "8S", "9S", "TS", "JS"));
        List<Card> cards3 = TestUtil.from(List.of("TC", "JC", "QC", "KC", "AC"));
        List<Card> cards4 = TestUtil.from(List.of("5D", "6S", "7C", "8D", "9H"));

        List<Card> cards5 = TestUtil.from(List.of("6D", "QC", "9D", "TD", "QD"));
        List<Card> cards6 = TestUtil.from(List.of("6H", "7D", "8S", "9D", "5D"));
        List<Card> cards7 = TestUtil.from(List.of("AC", "2S", "3D", "4H", "5D"));
        List<Card> cards8 = TestUtil.from(List.of("6D", "QD", "9D", "TD", "QD"));

        assertTrue(SimpleHandValueDecider.isStraight(cards1));
        assertTrue(SimpleHandValueDecider.isStraight(cards2));
        assertTrue(SimpleHandValueDecider.isStraight(cards3));
        assertTrue(SimpleHandValueDecider.isStraight(cards4));

        assertFalse(SimpleHandValueDecider.isStraight(cards5));
        assertFalse(SimpleHandValueDecider.isStraight(cards6));
        assertFalse(SimpleHandValueDecider.isStraight(cards7));
        assertFalse(SimpleHandValueDecider.isStraight(cards8));
    }

    private void assertHandValue(Combination expectedCombination,
                                 int expectedPrimaryValue,
                                 int expectedSecondaryValue,
                                 HandValue actualHandValue) {
        assertEquals(expectedCombination, actualHandValue.getCombination());
        assertEquals(expectedPrimaryValue, actualHandValue.getPrimaryValue());
        assertEquals(expectedSecondaryValue, actualHandValue.getSecondaryValue());
    }
}
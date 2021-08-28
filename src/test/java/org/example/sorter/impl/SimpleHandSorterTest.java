package org.example.sorter.impl;

import org.example.model.Combination;
import org.example.model.Hand;
import org.example.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Joshua Xing
 */
class SimpleHandSorterTest {

    @Test
    void sortTest() {
        Hand hand0 = TestUtil.from(List.of("AH", "2C", "3H", "4D", "5S"), Combination.HIGH_CARD, 0, 0);
        Hand hand1 = TestUtil.from(List.of("AH", "2C", "3H", "4D", "5S"), Combination.HIGH_CARD, 0, 0);
        Hand hand2 = TestUtil.from(List.of("AH", "2C", "2D", "KS", "9D"), Combination.PAIR, 2, 0);
        Hand hand3 = TestUtil.from(List.of("AH", "2C", "2D", "KS", "KD"), Combination.TWO_PAIRS, 13, 2);
        Hand hand4 = TestUtil.from(List.of("AH", "2C", "2D", "2S", "9D"), Combination.THREE_OF_A_KIND, 2, 0);
        Hand hand5 = TestUtil.from(List.of("5H", "8C", "4D", "7S", "6D"), Combination.STRAIGHT, 0, 0);
        Hand hand6 = TestUtil.from(List.of("AC", "2C", "3C", "KC", "9C"), Combination.FLUSH, 0, 0);
        Hand hand7 = TestUtil.from(List.of("AH", "2C", "2D", "AS", "AD"), Combination.FULL_HOUSE, 14, 0);
        Hand hand8 = TestUtil.from(List.of("2H", "2C", "2D", "KS", "2S"), Combination.FOUR_OF_A_KIND, 2, 0);
        Hand hand9 = TestUtil.from(List.of("QD", "JD", "9D", "KD", "TD"), Combination.STRAIGHT_FLUSH, 0, 0);
        Hand hand10 = TestUtil.from(List.of("AH", "KH", "TH", "QH", "JH"), Combination.ROYAL_FLUSH, 0, 0);
        Hand hand11 = TestUtil.from(List.of("KH", "QC", "3H", "4D", "5S"), Combination.HIGH_CARD, 0, 0);
        Hand hand12 = TestUtil.from(List.of("AH", "2C", "9S", "KS", "9D"), Combination.PAIR, 9, 0);
        Hand hand13 = TestUtil.from(List.of("7H", "2C", "2D", "7S", "KD"), Combination.TWO_PAIRS, 13, 2);
        Hand hand14 = TestUtil.from(List.of("AH", "9C", "2D", "9S", "9D"), Combination.THREE_OF_A_KIND, 2, 0);
        Hand hand15 = TestUtil.from(List.of("5H", "3C", "4D", "7S", "6D"), Combination.STRAIGHT, 0, 0);
        Hand hand16 = TestUtil.from(List.of("AD", "2D", "TD", "KD", "9D"), Combination.FLUSH, 0, 0);
        Hand hand17 = TestUtil.from(List.of("2H", "2C", "2D", "AS", "AD"), Combination.FULL_HOUSE, 14, 0);
        Hand hand18 = TestUtil.from(List.of("KH", "KC", "KD", "KS", "2S"), Combination.FOUR_OF_A_KIND, 2, 0);
        Hand hand19 = TestUtil.from(List.of("QC", "JC", "9C", "8C", "TC"), Combination.STRAIGHT_FLUSH, 0, 0);
        Hand hand20 = TestUtil.from(List.of("AS", "KS", "TS", "QS", "JS"), Combination.ROYAL_FLUSH, 0, 0);

        SimpleHandSorter sorter = new SimpleHandSorter();
        assertEquals(0, sorter.sort(hand0, hand1));
        assertTrue(sorter.sort(hand1, hand2) < 0);
        assertTrue(sorter.sort(hand1, hand11) > 0);
        assertTrue(sorter.sort(hand2, hand12) < 0);
        assertTrue(sorter.sort(hand3, hand13) > 0);
        assertTrue(sorter.sort(hand4, hand14) < 0);
        assertTrue(sorter.sort(hand5, hand15) > 0);
        assertTrue(sorter.sort(hand6, hand16) < 0);
        assertTrue(sorter.sort(hand7, hand17) > 0);
        assertTrue(sorter.sort(hand8, hand18) < 0);
        assertTrue(sorter.sort(hand9, hand19) > 0);
        assertEquals(0, sorter.sort(hand10, hand20));
    }
}
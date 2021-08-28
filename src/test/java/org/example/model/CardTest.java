package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Joshua Xing
 */
class CardTest {
    @Test
    void fromTest() {
        assertEquals("4H", Card.from("4H").toString());
        assertEquals("10D", Card.from("TD").toString());
        assertEquals("11C", Card.from("JC").toString());
        assertEquals("12S", Card.from("QS").toString());
        assertEquals("13H", Card.from("KH").toString());
        assertEquals("14C", Card.from("AC").toString());
    }

    @Test
    void fromInvalidCardTest() {
        assertThrows(IllegalArgumentException.class, () -> Card.from("0H"), "Invalid card: 0H");
        assertThrows(IllegalArgumentException.class, () -> Card.from("2M"), "Invalid card: 2M");
        assertThrows(IllegalArgumentException.class, () -> Card.from("0M"), "Invalid card: 0M");
        assertThrows(IllegalArgumentException.class, () -> Card.from("11D"), "Invalid card: 11D");
        assertThrows(IllegalArgumentException.class, () -> Card.from("aD"), "Invalid card: aD");
        assertThrows(IllegalArgumentException.class, () -> Card.from("9c"), "Invalid card: 9c");
        assertThrows(IllegalArgumentException.class, () -> Card.from("A"), "Invalid card: A");
    }

    @Test
    void compareToTest() {
        Card card1 = Card.from("4H");
        Card card2 = Card.from("TD");
        Card card3 = Card.from("4S");
        Card card4 = Card.from("AC");

        assertEquals(0, card1.compareTo(card3));
        assertTrue(card1.compareTo(card2) < 0);
        assertTrue(card4.compareTo(card2) > 0);
    }
}
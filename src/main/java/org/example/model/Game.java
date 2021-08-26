package org.example.model;

import org.example.sorter.HandValueDecider;
import org.example.sorter.HandSorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Joshua Xing
 */
public class Game {
    private static final int CARDS_PER_HAND = 5;

    private final int playerCount;
    private final HandSorter sorter;
    private final HandValueDecider decider;
    private final int[] scores;

    public Game(int playerCount, HandSorter sorter, HandValueDecider decider) {
        if (playerCount < 2) {
            throw new IllegalArgumentException("Expecting at least 2 players, but got " + playerCount);
        }
        this.playerCount = playerCount;
        this.sorter = sorter;
        this.decider = decider;
        this.scores = new int[playerCount + 1]; // extra one for tie
        Arrays.fill(scores, 0);
    }

    public void addHands(String cardsStr) {
        String[] cardTokens = cardsStr.split(" ");
        int expectedCardNo = CARDS_PER_HAND * playerCount;
        if (cardTokens.length < expectedCardNo) {
            System.out.printf("Expecting %d cards in one line, but got %d\n", expectedCardNo, cardTokens.length);
        }
        // split all input cards to each player
        List<Card> cards = Arrays.stream(cardTokens).map(Card::from).collect(Collectors.toList());
        List<Hand> hands = new ArrayList<>(playerCount);
        for (int i = 0; i < playerCount; ++i) {
            List<Card> cardPerHand = cards.subList(i * CARDS_PER_HAND, (i + 1) * CARDS_PER_HAND);
            HandValue value = decider.getHandValue(cardPerHand);
            hands.add(new Hand(cardPerHand, value));
        }

        // compare each pair of hands to get the winner
        int winnerIndex = 0;
        int totalResult = 0;
        for (int i = 1; i < playerCount; ++i) {
            int result = sorter.sort(hands.get(winnerIndex), hands.get(i));
            totalResult += result;
            if (result < 0) {
                winnerIndex = i;
            }
        }
        // if all results are 0, means all hands are the same
        if (totalResult == 0) {
            ++scores[playerCount];
        } else {
            ++scores[winnerIndex];
        }
    }

    public void printResult() {
        for (int i = 0; i < playerCount; ++i) {
            System.out.printf("Player %d: %d\n", i, scores[i]);
        }
        System.out.println("Tie: " + scores[playerCount]);
    }
}

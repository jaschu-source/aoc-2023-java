package org.jaschu.christmas.fun.day07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CamelCardGame {
    private final List<CamelCardHand> camelCardHands;

    public CamelCardGame(List<String> input) {
        camelCardHands = new ArrayList<>();
        input.forEach(hand -> camelCardHands.add(new CamelCardHand(hand)));
        Collections.sort(camelCardHands);
    }

    public CamelCardGame(List<String> input, boolean withJoker) {
        camelCardHands = new ArrayList<>();
        input.forEach(hand -> camelCardHands.add(new CamelCardHandWithJoker(hand)));
        Collections.sort(camelCardHands);
    }

    public int getTotalWinnings() {
        int total = 0;
        for (int i = 1; i <= camelCardHands.size(); i++) {
            total += camelCardHands.get(i - 1).getScore(i);
        }
        return total;
    }


}

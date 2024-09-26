package org.jaschu.christmas.fun.day07;

import java.util.List;

public class CamelCardGame {
    private List<CamelCardHand> camelCardHands;

    public CamelCardGame(List<String> input) {
        input.forEach(hand -> {
            camelCardHands.add(new CamelCardHand(hand));
        });
    }
}

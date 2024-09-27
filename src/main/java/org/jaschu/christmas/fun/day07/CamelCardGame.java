package org.jaschu.christmas.fun.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CamelCardGame {
    private List<CamelCardHand> camelCardHands;

    public CamelCardGame(List<String> input) {
        camelCardHands = new ArrayList<>();
        input.forEach(hand -> camelCardHands.add(new CamelCardHand(hand)));
    }

    public int getTotalWinnings() {
        AtomicInteger total = new AtomicInteger();
        // TODO evaluate each hands rank compared to the other hands
        camelCardHands.forEach(camelCardHand -> total.set(total.get() + camelCardHand.getScore()));
        return total.get();
    }


}

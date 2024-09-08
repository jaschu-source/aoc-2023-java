package org.jaschu.christmas.fun.day04;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ScratchCard {
    private List<Integer> winningNumbers; // number needed to win
    private List<Integer> playerNumbers; // numbers on the scratchcard

    /**
     * expects a string representing a card, with format Card <i>numberOfCard</i>: <i>winningNumbers</i> | <i>playerNumbers</i>
     */
    public ScratchCard(String cardString) {
        String[] cardInfos = cardString.split(":\\s");
        if (cardInfos.length == 2) {
            String cardDetailString = cardInfos[1];
            String[] cardDetails = cardDetailString.split("\\s\\|\\s");
            this.winningNumbers = Arrays.stream(cardDetails[0].split("\\s+")).map(Integer::parseInt).toList();
            this.playerNumbers = Arrays.stream(cardDetails[1].split("\\s+")).map(Integer::parseInt).toList();
        } else {
            throw new IllegalArgumentException("Input string doesn't meet requirements for scratch card generation.");
        }

    }
}

package org.jaschu.christmas.fun.day04;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Data
public class ScratchCard {
    private List<Integer> winningNumbers; // number needed to win
    private List<Integer> playerNumbers; // numbers on the scratchcard
    private int ownedCopies;

    /**
     * expects a string representing a card, with format "Card <i>numberOfCard</i>: <i>winningNumbers</i> | <i>playerNumbers</i>"<br/>
     * example<br/>
     * Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
     */
    public ScratchCard(String cardString) {
        this.ownedCopies = 1;
        String[] cardInfos = cardString.split(":\\s+");
        if (cardInfos.length == 2) {
            String cardDetailString = cardInfos[1];
            List<String> cardDetails = Arrays.stream(cardDetailString.split("\\s\\|\\s+")).toList();

            this.winningNumbers = Arrays.stream(cardDetails.get(0).split("\\s+"))
                    .map((detail) -> isNumeric(detail) ? Integer.parseInt(detail) : null).toList();
            this.playerNumbers = Arrays.stream(cardDetails.get(1).split("\\s+"))
                    .map((detail) -> isNumeric(detail) ? Integer.parseInt(detail) : null).toList();
        } else {
            throw new IllegalArgumentException("Input string doesn't meet requirements for scratch card generation.");
        }
    }

    public int getPoints() {
        AtomicInteger points = new AtomicInteger(0);

        winningNumbers.forEach(winningNumber -> {
            if (winningNumber != null && playerNumbers.contains(winningNumber)) {
                if (points.get() > 0) {
                    points.set(points.get() * 2);
                } else {
                    points.set(1);
                }
            }
        });

        return points.get();
    }

    public int calculateNumberOfBonusCards() {
        AtomicInteger points = new AtomicInteger(0);

        winningNumbers.forEach(winningNumber -> {
            if (winningNumber != null && playerNumbers.contains(winningNumber)) {
                if (points.get() > 0) {
                    points.addAndGet(1);
                } else {
                    points.set(1);
                }
            }
        });

        return points.get();
    }

}

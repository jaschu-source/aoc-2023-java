package org.jaschu.christmas.fun.day07;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CamelCardHand {
    private List<Card> heldCards;
    private final int bidAmount;

    public CamelCardHand(String input) {
        heldCards = new ArrayList<>();
        String[] cardParameters = input.split("\\s+");
        if (cardParameters.length == 2) {
            bidAmount = Integer.parseInt(cardParameters[1]);
            String hand = cardParameters[0];
            List<Character> characters = hand.chars().mapToObj(c -> (char) c).toList();
            int i = 0;

            while (!hand.isEmpty()) {
                String currentCharacter = String.valueOf(characters.get(i));
                if (heldCards.stream().anyMatch(card -> !card.getTypeOfCard().equals(currentCharacter))) {
                    heldCards.add(new Card(currentCharacter, StringUtils.countMatches(input, currentCharacter)));
                    // TODO replace doesnt work
                    hand = hand.replaceAll(currentCharacter, "");
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("Wrong input format for camel card hand.");
        }
    }
}

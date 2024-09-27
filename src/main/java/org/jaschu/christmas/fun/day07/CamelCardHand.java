package org.jaschu.christmas.fun.day07;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CamelCardHand {
    private final List<Card> heldCards;

    private final int bidAmount;

    @Getter
    private float handTypePriority;

    @Getter
    @Setter
    private int rank;

    public CamelCardHand(String input) {
        rank = 0;
        heldCards = new ArrayList<>();
        String[] cardParameters = input.split("\\s+");
        if (cardParameters.length == 2) {
            bidAmount = Integer.parseInt(cardParameters[1]);
            String hand = cardParameters[0];
            List<Character> characters = hand.chars().mapToObj(c -> (char) c).toList();
            int i = 0;

            while (!hand.isEmpty()) {
                String currentCharacter = String.valueOf(characters.get(i));
                if (heldCards.stream().filter(card -> card.getTypeOfCard().equals(currentCharacter)).toList().isEmpty()) {
                    heldCards.add(new Card(currentCharacter, StringUtils.countMatches(input, currentCharacter)));
                    hand = hand.replaceAll(currentCharacter, "");
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("Wrong input format for camel card hand.");
        }

        evaluateHandType();
    }

    public int getScore() {
        return rank * bidAmount;
    }

    /**
     * 1 Five of a kind
     * 2.1 Four of a kind // 2.2 Full house // 3.1 Three of a kind // 3.2 Two pair
     * 4 One pair // 5 High card
     */
    private void evaluateHandType() {
        // I really have to evaluate the type of hand to compare with others in the same game
//        handTypePriority = this.heldCards.stream().collect(Collectors.groupingBy(Card::getTypeOfCard, Collectors.counting())).size();
        handTypePriority = this.heldCards.size();
        // needs to be evaluated more
    }
}

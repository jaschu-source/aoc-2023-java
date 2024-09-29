package org.jaschu.christmas.fun.day07;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CamelCardHand implements Comparable<CamelCardHand> {
    private static final Map<String, Integer> possibleCardValuesMap = Map.of(
            "A", 1, "K", 2, "Q", 3, "J", 4, "T", 5
    );

    @Getter
    private final String heldCardsString;
    private final List<Card> heldCards;

    private final int bidAmount;

    /**
     * rank of card in general
     */
    @Getter
    private double handTypePriority;

    public CamelCardHand(String input) {
        heldCards = new ArrayList<>();
        String[] cardParameters = input.split("\\s+");
        if (cardParameters.length == 2) {
            bidAmount = Integer.parseInt(cardParameters[1]);
            String hand = cardParameters[0];
            heldCardsString = hand;
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

    public int getScore(int rankInGame) {
        return rankInGame * bidAmount;
    }

    /**
     * 1 Five of a kind
     * 2.1 Four of a kind // 2.2 Full house // 3.1 Three of a kind // 3.2 Two pair
     * 4 One pair // 5 High card
     */
    private void evaluateHandType() {
        // the hand needs to be evaluated to compare with others in the same game
//        handTypePriority = this.heldCards.stream().collect(Collectors.groupingBy(Card::getTypeOfCard, Collectors.counting())).size();
        handTypePriority = this.heldCards.size();

        if (handTypePriority == 2) {
            handTypePriority = this.heldCards.stream().anyMatch(card -> card.getNumberOfOwnedCopies() == 4) ? 2.1 : 2.2;
        }

        if (handTypePriority == 3) {
            handTypePriority = this.heldCards.stream().anyMatch(card -> card.getNumberOfOwnedCopies() == 3) ? 3.1 : 3.2;
        }
    }

    @Override
    public int compareTo(CamelCardHand handToCompareTo) {
        int compareResult = 0;
        if (handToCompareTo.getHeldCardsString().equals(this.heldCardsString)) return compareResult;
        compareResult = this.handTypePriority <= handToCompareTo.handTypePriority ? 1 : -1;
        if (this.handTypePriority == handToCompareTo.handTypePriority) {
            for (int i = 0; i < this.heldCardsString.length(); i++) {
                String value = this.heldCardsString.substring(i, i + 1);
                String valueToCompareTo = handToCompareTo.heldCardsString.substring(i, i + 1);
                if (!value.equals(valueToCompareTo)) {
                    compareResult = isCardValueHigher(value, valueToCompareTo) ? 1 : -1;
                    break;
                }
            }
        }
        return compareResult;
    }

    private boolean isCardValueHigher(String value, String valueToCompareTo) {
        // don't forget to prevent same value before calling this method
        boolean higher;
        if (StringUtils.isNumeric(value)) {
            higher = StringUtils.isNumeric(valueToCompareTo) && Integer.parseInt(value) > Integer.parseInt(valueToCompareTo);
        } else {
            higher = StringUtils.isNumeric(valueToCompareTo) || isCardValueHigherNonNumeric(value, valueToCompareTo);
        }
        return higher;
    }

    private boolean isCardValueHigherNonNumeric(String value, String valueToCompareTo) {
        return possibleCardValuesMap.get(value) < possibleCardValuesMap.get(valueToCompareTo);
    }
}

package org.jaschu.christmas.fun.day07;

import org.apache.commons.lang3.StringUtils;

public class CamelCardHandWithJoker extends CamelCardHand {
    public CamelCardHandWithJoker(String input) {
        super(input, true);
        checkForJokerAndEvaluate();
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
            higher = valueToCompareTo.equals("J") || (StringUtils.isNumeric(valueToCompareTo) && Integer.parseInt(value) > Integer.parseInt(valueToCompareTo));
        } else {
            higher = !value.equals("J") && (StringUtils.isNumeric(valueToCompareTo) || isCardValueHigherNonNumeric(value, valueToCompareTo));
        }
        return higher;
    }

    /**
     * 1 Five of a kind
     * 2.1 Four of a kind // 2.2 Full house // 3.1 Three of a kind // 3.2 Two pair
     * 4 One pair // 5 High card
     */
    private void checkForJokerAndEvaluate() {
        if (this.heldCards.stream().anyMatch(card -> card.getTypeOfCard().equals("J"))) {
            evaluateHandWithJoker();
        } else {
            super.evaluateHandType();
        }
    }

    private void evaluateHandWithJoker() {
        int numberOfCards = this.heldCards.size();
        if (numberOfCards == 1 || numberOfCards == 2) {
            // with joker its always five of a kind
            handTypePriority = 1;
            return;
        }

        if (numberOfCards == 3) {
            handTypePriority = this.heldCards.stream().anyMatch(card -> card.getNumberOfOwnedCopies() == 3) ? 2.1 : 2.2;
            if (handTypePriority == 2.2) {
                handTypePriority = this.heldCards.stream().filter(card -> card.getTypeOfCard().equals("J")).findFirst().get().getNumberOfOwnedCopies() == 2 ? 2.1 : 2.2;
            }
            return;
        }

        if (numberOfCards == 4) {
            handTypePriority = this.heldCards.stream().anyMatch(card -> card.getNumberOfOwnedCopies() == 2) ? 3.1 : 3.2;
            return;
        }

        handTypePriority = 4;
    }

}

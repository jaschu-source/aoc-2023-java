package org.jaschu.christmas.fun.day07;

import lombok.Getter;

@Getter
public class Card {
    private final String typeOfCard;
    private int numberOfOwnedCopies;

    public Card(String typeOfCard, int numberOfOwnedCopies) {
        this.typeOfCard = typeOfCard;
        this.numberOfOwnedCopies = numberOfOwnedCopies;
    }
}

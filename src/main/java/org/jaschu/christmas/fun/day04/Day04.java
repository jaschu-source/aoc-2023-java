package org.jaschu.christmas.fun.day04;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day04 extends AbstractPuzzle {
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day04(String puzzleFileName) {
        super(puzzleFileName);
    }

    @Override
    public String solvePart1() {
        AtomicInteger finalPoints = new AtomicInteger();
        lines.forEach(line -> {
            ScratchCard scratchCard = new ScratchCard(line);
            finalPoints.addAndGet(scratchCard.getPoints());
        });
        return String.valueOf(finalPoints.get());
    }

    @Override
    public String solvePart2() {
        List<ScratchCard> scratchCards = lines.stream().map(ScratchCard::new).toList();
        for (int i = 0; i < scratchCards.size(); i++) {
            calculateOwnedCopies(scratchCards, i);
        }
        return String.valueOf(scratchCards.stream().mapToInt(ScratchCard::getOwnedCopies).sum());
    }

    private static void calculateOwnedCopies(List<ScratchCard> scratchCards, int cardPosition) {
        int numberAdditionalCopies = scratchCards.get(cardPosition).calculateNumberOfBonusCards();
        for (int j = 1;
             j <= numberAdditionalCopies && cardPosition + j < scratchCards.size(); j++) {
            scratchCards.get(cardPosition + j).setOwnedCopies(scratchCards.get(cardPosition + j).getOwnedCopies() + 1);
            // the copies that are won also win more copies
            calculateOwnedCopies(scratchCards, cardPosition + j);
        }
    }
}

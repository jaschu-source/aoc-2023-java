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
        List<String> lines = this.readFileLines();
        AtomicInteger finalPoints = new AtomicInteger();
        lines.forEach(line -> {
            ScratchCard scratchCard = new ScratchCard(line);
            finalPoints.addAndGet(scratchCard.getPoints());
        });
        return String.valueOf(finalPoints.get());
    }

    @Override
    public String solvePart2() {
        List<String> lines = this.readFileLines();
        List<ScratchCard> scratchCards = lines.stream().map(ScratchCard::new).toList();
        for (int i = 0; i < scratchCards.size(); i++) {
            int numberAdditionalCopies = scratchCards.get(i).calculateNumberOfBonusCards();
            for (int j = 1;
                 j <= numberAdditionalCopies && i + j < scratchCards.size(); j++) {
                // the copies that are won also win more copies
                // recursion ?
                scratchCards.get(i + j).setOwnedCopies(scratchCards.get(i + j).getOwnedCopies() + 1);
            }
        }
        return null;
    }
}

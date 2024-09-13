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
        return null;
    }
}

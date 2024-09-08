package org.jaschu.christmas.fun.day04;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

import java.util.List;

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
        lines.forEach(line -> {
            ScratchCard scratchCard = new ScratchCard(line);
        });
        return null;
    }

    @Override
    public String solvePart2() {
        return null;
    }
}

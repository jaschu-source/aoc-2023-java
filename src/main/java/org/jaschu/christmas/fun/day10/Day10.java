package org.jaschu.christmas.fun.day10;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

public class Day10 extends AbstractPuzzle {
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day10(String puzzleFileName) {
        super(puzzleFileName);
    }

    @Override
    public String solvePart1() {
        PipeMaze pipeMaze = new PipeMaze(lines);
        return String.valueOf(pipeMaze.searchStepsToFurthestPoint());
    }

    @Override
    public String solvePart2() {
        return null;
    }
}

package org.jaschu.christmas.fun.day05;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

import java.util.List;

public class Day05 extends AbstractPuzzle {
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day05(String puzzleFileName) {
        super(puzzleFileName);
    }

    @Override
    public String solvePart1() {
        List<String> lines = this.readFileLines();
        Almanac almanac = new Almanac(lines);
        return almanac.getClosestLocation();
    }

    @Override
    public String solvePart2() {
        return null;
    }
}

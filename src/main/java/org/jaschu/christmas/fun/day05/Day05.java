package org.jaschu.christmas.fun.day05;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

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
        Almanac almanac = new Almanac(lines);
        return almanac.getClosestLocation();
    }

    /**
     * caution this is very slow for big values
     */
    @Override
    public String solvePart2() {
        Almanac almanac = new Almanac(lines);
        return almanac.getClosestLocationFromSeedRange();
    }
}

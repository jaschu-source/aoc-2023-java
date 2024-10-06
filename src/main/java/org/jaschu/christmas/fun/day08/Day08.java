package org.jaschu.christmas.fun.day08;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

public class Day08 extends AbstractPuzzle {
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day08(String puzzleFileName) {
        super(puzzleFileName);
    }

    @Override
    public String solvePart1() {
        Network network = new Network(lines);
        return String.valueOf(network.searchDestination());
    }

    @Override
    public String solvePart2() {
        return null;
    }
}

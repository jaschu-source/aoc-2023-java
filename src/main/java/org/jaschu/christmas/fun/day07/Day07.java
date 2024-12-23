package org.jaschu.christmas.fun.day07;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

public class Day07 extends AbstractPuzzle {
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day07(String puzzleFileName) {
        super(puzzleFileName);
    }

    @Override
    public String solvePart1() {
        CamelCardGame camelCardGame = new CamelCardGame(lines);
        return String.valueOf(camelCardGame.getTotalWinnings());
    }

    @Override
    public String solvePart2() {
        CamelCardGame camelCardGame = new CamelCardGame(lines, true);
        return String.valueOf(camelCardGame.getTotalWinnings());
    }
}

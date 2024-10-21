package org.jaschu.christmas.fun.day09;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

public class Day09 extends AbstractPuzzle {
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day09(String puzzleFileName) {
        super(puzzleFileName);
    }

    @Override
    public String solvePart1() {
        OasisAndSandInstabilitySensor sensor = new OasisAndSandInstabilitySensor(lines);
        return sensor.analyzeHistories(false);
    }

    @Override
    public String solvePart2() {
        OasisAndSandInstabilitySensor sensor = new OasisAndSandInstabilitySensor(lines);
        return sensor.analyzeHistories(true);
    }
}

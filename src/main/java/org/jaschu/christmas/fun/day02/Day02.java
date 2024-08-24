package org.jaschu.christmas.fun.day02;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day02 extends AbstractPuzzle {
    // cubeGame that determines if a game is possible or not
    CubeGame cubeGame;
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day02(String puzzleFileName, int red, int green, int blue) {
        super(puzzleFileName);
        cubeGame = CubeGame.builder().identifyingNumber(0).redCubes(red).greenCubes(green).blueCubes(blue).build();
    }

    @Override
    public String solvePart1() {
        List<String> lines = this.readFileLines();
        AtomicInteger possibleGamesSum = new AtomicInteger();
        lines.forEach(line -> {
            CubeGame possibleGame = CubeGame.generateCubeGame(line);
            if(possibleGame.isPossibleWithCubes(cubeGame.getRedCubes(), cubeGame.getGreenCubes(), cubeGame.getBlueCubes())) possibleGamesSum.addAndGet(possibleGame.getIdentifyingNumber());
        });
        return String.valueOf(possibleGamesSum.get());
    }

    @Override
    public String solvePart2() {
        List<String> lines = this.readFileLines();
        AtomicInteger possibleGamesSum = new AtomicInteger();
        lines.forEach(line -> {
            CubeGame possibleGame = CubeGame.generateCubeGame(line);
            int gamePower = possibleGame.getRedCubes() * possibleGame.getGreenCubes() * possibleGame.getBlueCubes();
            possibleGamesSum.addAndGet(gamePower);
        });
        return String.valueOf(possibleGamesSum.get());
    }
}

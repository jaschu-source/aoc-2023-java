package org.jaschu.christmas.fun.common;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An abstract class that represents a solution to an Advent of Code puzzle.
 */
public abstract class AbstractPuzzle {
    private final String _puzzleInput;

    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public AbstractPuzzle(String puzzleFileName) {
        _puzzleInput = puzzleFileName;
    }

    /**
     * Gets the puzzle input for this day's puzzle.
     *
     * @return the puzzle input for this day's puzzle
     */
    public String getPuzzleInput() {
        return _puzzleInput;
    }

    public Path getPath() throws URISyntaxException {
        return Paths.get(this.getClass().getClassLoader().getResource(_puzzleInput).toURI());
    }

    /**
     * Returns the solution to part 1 of the puzzle.
     *
     * @return the solution to part 1 of the puzzle
     */
    public abstract String solvePart1();

    /**
     * Returns the solution to part 2 of the puzzle.
     *
     * @return the solution to part 2 of the puzzle
     */
    public abstract String solvePart2();
}

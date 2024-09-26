package org.jaschu.christmas.fun.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * An abstract class that represents a solution to an Advent of Code puzzle.
 */
public abstract class AbstractPuzzle {
    private final String _puzzleInput;
    public final List<String> lines;

    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public AbstractPuzzle(String puzzleFileName) {
        _puzzleInput = puzzleFileName;
        lines = this.readFileLines();
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

    public List<String> readFileLines() {
        try {
            return InputReader.readFileInputLines(_puzzleInput);
        } catch (IOException e) {
            System.out.println("Failed to read file " + _puzzleInput);
            System.exit(0);
        }

        return null;
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

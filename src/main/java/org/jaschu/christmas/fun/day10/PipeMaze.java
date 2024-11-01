package org.jaschu.christmas.fun.day10;

import org.jaschu.christmas.fun.common.CardinalDirection;

import java.util.ArrayList;
import java.util.List;

public class PipeMaze {
    private final String[][] map;
    private List<CoordinateWithSteps> coordinates;
    private List<Pipe> possiblePipes;

    public PipeMaze(List<String> input) {
        // Initialize map based on the list size and string lengths
        map = new String[input.size()][];

        for (int i = 0; i < input.size(); i++) {
            String str = input.get(i);
            map[i] = new String[str.length()]; // Initialize the inner array for each string's length

            for (int j = 0; j < str.length(); j++) {
                map[i][j] = String.valueOf(str.charAt(j)); // Store each character as a String
            }
        }

        coordinates = new ArrayList<>();
        initializePipes();
    }

    public int searchStepsToFurthestPoint() {
        findStartingPosition();

        do {  // traverse the pipe from both sides until the coordinates meet
            for (CoordinateWithSteps coordinate : coordinates) {
                findAndGoToNextCoordinate(coordinate);
            }
        } while (coordinates.get(1).getX() != coordinates.get(2).getX() && coordinates.get(1).getY() != coordinates.get(2).getY());
        return coordinates.get(1).getSteps(); // the coordinates overlap in the middle of the pipe so it doesn't matter which one the steps are taken from
    }

    private void findStartingPosition() {
        boolean startingPositionFound = false;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (isStartingPoint(map[i][j])) {
                    coordinates.add(new CoordinateWithSteps(j, i));
                    coordinates.add(new CoordinateWithSteps(j, i));
                    startingPositionFound = true;
                    break;
                }
            }
            if (startingPositionFound) break;
        }
    }

    public boolean isStartingPoint(String positionValue) {
        return positionValue.equals("S");
    }

    /**
     * | is a vertical pipe connecting north and south.
     * - is a horizontal pipe connecting east and west.
     * L is a 90-degree bend connecting north and east.
     * J is a 90-degree bend connecting north and west.
     * 7 is a 90-degree bend connecting south and west.
     * F is a 90-degree bend connecting south and east.
     * . is ground; there is no pipe in this tile.
     * S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.
     */
    private void initializePipes() {
        possiblePipes = new ArrayList<>();
        possiblePipes.add(new Pipe("|", true, false, true, false));
        possiblePipes.add(new Pipe("-", false, true, false, true));
        possiblePipes.add(new Pipe("L", true, true, false, false));
        possiblePipes.add(new Pipe("J", true, false, false, true));
        possiblePipes.add(new Pipe("7", false, false, true, true));
        possiblePipes.add(new Pipe("F", false, true, true, false));
        possiblePipes.add(new Pipe(".", false, false, false, false));

    }

    private void findAndGoToNextCoordinate(CoordinateWithSteps coordinate) {
        goToNextCoordinate(coordinate, findNextTurn(coordinate));
    }

    private String findNextTurn(CoordinateWithSteps coordinate) {
        String nextTurn = "";
        nextTurn = map[coordinate.getY()][coordinate.getX() + 1];

        return nextTurn;
    }

    private void goToNextCoordinate(CoordinateWithSteps coordinate, String turn, CardinalDirection enteredFrom) {
        switch (turn) {
            case "|":
                if (enteredFrom == CardinalDirection.NORTH)
                    coordinate.updatePosition(coordinate.getX(), coordinate.getY() + 1);
                if (enteredFrom == CardinalDirection.SOUTH)
                    coordinate.updatePosition(coordinate.getX(), coordinate.getY() - 1);
                break;
            case "-":
                if (enteredFrom == CardinalDirection.WEST)
                    coordinate.updatePosition(coordinate.getX() + 1, coordinate.getY());
                if (enteredFrom == CardinalDirection.EAST)
                    coordinate.updatePosition(coordinate.getX() - 1, coordinate.getY());
                break;
            case "L", "7":
                if (enteredFrom == CardinalDirection.NORTH || enteredFrom == CardinalDirection.WEST)
                    coordinate.updatePosition(coordinate.getX() + 1, coordinate.getY() + 1);
                if (enteredFrom == CardinalDirection.EAST || enteredFrom == CardinalDirection.SOUTH)
                    coordinate.updatePosition(coordinate.getX() - 1, coordinate.getY() - 1);
                break;
            case "J":
                if (enteredFrom == CardinalDirection.WEST)
                    coordinate.updatePosition(coordinate.getX() + 1, coordinate.getY() - 1);
                if (enteredFrom == CardinalDirection.NORTH)
                    coordinate.updatePosition(coordinate.getX() - 1, coordinate.getY() + 1);
                break;
            case "F":
                if (enteredFrom == CardinalDirection.EAST)
                    coordinate.updatePosition(coordinate.getX() - 1, coordinate.getY() + 1);
                if (enteredFrom == CardinalDirection.SOUTH)
                    coordinate.updatePosition(coordinate.getX() + 1, coordinate.getY() - 1);
                break;
        }
    }
}

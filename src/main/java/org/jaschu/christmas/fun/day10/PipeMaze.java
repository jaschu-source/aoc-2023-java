package org.jaschu.christmas.fun.day10;

import org.jaschu.christmas.fun.common.CardinalDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class PipeMaze {
    private final Pipe[][] map;
    private final List<CoordinateWithSteps> coordinates;
    private Map<String, Pipe> pipeMap;

    public PipeMaze(List<String> input) {
        initializePipes();

        // Initialize map based on the list size and string lengths
        map = input.stream()
                .map(str -> str.chars()
                        .mapToObj(c -> pipeMap.get(String.valueOf((char) c)))
                        .toArray(Pipe[]::new))
                .toArray(Pipe[][]::new);

        coordinates = new ArrayList<>();
    }

    public int searchStepsToFurthestPoint() {
        findStartingPosition();

        do {  // traverse the pipe from both sides until the coordinates meet
            for (CoordinateWithSteps coordinate : coordinates) {
                findAndGoToNextCoordinate(coordinate);
            }
        } while (coordinates.get(0).getX() != coordinates.get(1).getX() && coordinates.get(0).getY() != coordinates.get(1).getY());
        return coordinates.get(0).getSteps(); // the coordinates overlap in the middle of the pipe, so it doesn't matter which one the steps are taken from
    }

    private void findStartingPosition() {
        boolean startingPositionFound = false;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (isStartingPoint(map[i][j].getValue())) {
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

    private void initializePipes() {
        List<Pipe> possiblePipes = new ArrayList<>();
        possiblePipes.add(new Pipe("|", true, false, true, false));
        possiblePipes.add(new Pipe("-", false, true, false, true));
        possiblePipes.add(new Pipe("L", true, true, false, false));
        possiblePipes.add(new Pipe("J", true, false, false, true));
        possiblePipes.add(new Pipe("7", false, false, true, true));
        possiblePipes.add(new Pipe("F", false, true, true, false));
        possiblePipes.add(new Pipe(".", false, false, false, false));
        possiblePipes.add(new Pipe("S", false, false, false, false));

        // Create a lookup map from symbol to Pipe object
        pipeMap = new HashMap<>();
        for (Pipe pipe : possiblePipes) {
            pipeMap.put(pipe.getValue(), pipe);
        }

    }

    private void findAndGoToNextCoordinate(CoordinateWithSteps coordinate) {
        Pipe nextTurn;
        CardinalDirection enteredFrom;
        try {
            //check east
            nextTurn = map[coordinate.getY()][coordinate.getX() + 1];
            enteredFrom = CardinalDirection.WEST;
            if (nextTurn.isOpenToWest()) {
                goToNextCoordinate(coordinate, nextTurn.getValue(), enteredFrom);
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }

        try {
            // check south
            nextTurn = map[coordinate.getY() + 1][coordinate.getX()];
            enteredFrom = CardinalDirection.NORTH;
            if (nextTurn.isOpenToNorth()) {
                goToNextCoordinate(coordinate, nextTurn.getValue(), enteredFrom);
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }

        try {
            // check west
            nextTurn = map[coordinate.getY()][coordinate.getX() - 1];
            enteredFrom = CardinalDirection.EAST;
            if (nextTurn.isOpenToEast()) {
                goToNextCoordinate(coordinate, nextTurn.getValue(), enteredFrom);
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }

        try {
            // check north
            nextTurn = map[coordinate.getY() - 1][coordinate.getX()];
            enteredFrom = CardinalDirection.SOUTH;
            if (nextTurn.isOpenToSouth()) {
                goToNextCoordinate(coordinate, nextTurn.getValue(), enteredFrom);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }
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

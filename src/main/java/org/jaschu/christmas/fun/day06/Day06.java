package org.jaschu.christmas.fun.day06;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

import java.util.ArrayList;
import java.util.List;

public class Day06 extends AbstractPuzzle {
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day06(String puzzleFileName) {
        super(puzzleFileName);
    }

    @Override
    public String solvePart1() {
        if (lines.size() == 2) {
            String[] times = lines.get(0).split("\\s+");
            String[] distances = lines.get(1).split("\\s+");
            List<BoatRace> boatRaces = new ArrayList<>();
            if (times.length == distances.length) {
                for (int i = 1; i < times.length; i++) {
                    boatRaces.add(new BoatRace(Integer.parseInt(times[i]), Integer.parseInt(distances[i])));
                }

                Boat myBoat = new Boat(1);
                return myBoat.getMultipliedNumberOfWaysToWin(boatRaces);
            } else {
                throw new IllegalArgumentException("Incorrect Input Data for Day06.");
            }
        } else {
            throw new IllegalArgumentException("Incorrect Input Data for Day06.");
        }
    }

    @Override
    public String solvePart2() {
        String time = lines.get(0).replace("Time:", "").replaceAll("\\s+", "");
        String distance = lines.get(1).replace("Distance:", "").replaceAll("\\s+", "");
        BoatRace boatRace = new BoatRace(Integer.parseInt(time), Long.parseLong(distance));

        List<BoatRace> boatRaces = new ArrayList<>();
        boatRaces.add(boatRace);

        Boat myBoat = new Boat(1);
        return myBoat.getMultipliedNumberOfWaysToWin(boatRaces);
    }
}

package org.jaschu.christmas.fun.day03;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

import java.util.List;

public class Day03 extends AbstractPuzzle {
    /**
     * Constructor which accepts the puzzle input to be solved.
     *
     * @param puzzleFileName the puzzle input
     */
    public Day03(String puzzleFileName) {
        super(puzzleFileName);
    }

    @Override
    public String solvePart1() {
        List<String> lines = this.readFileLines();
        int schematicsSum = 0;
        // collect some additional info first
        List<List<SchematicsEntry>> schematicsEntries = lines.stream()
                .map((line) -> line.chars().mapToObj(c -> new SchematicsEntry((char) c)).toList())
                .toList();

        for(int rowNumber = 0; rowNumber < schematicsEntries.size(); rowNumber++) {
            for(int position = 0; position < schematicsEntries.get(rowNumber).size(); position++) {
                SchematicsEntry schematicsEntry = schematicsEntries.get(rowNumber).get(position);
                if(schematicsEntry.getSchematicsType().equals(SchematicsType.NUMBER) && !schematicsEntry.isNumberPart()) {
                    schematicsEntry.setNumberPart(true);
                    schematicsEntry.setFullNumber(schematicsEntries.get(rowNumber), position);
                    schematicsEntry.setSchematic(rowNumber, position, schematicsEntries, List.of(SchematicsType.SYMBOL, SchematicsType.ASTERISK));

                    if (schematicsEntry.isSchematic()) {
                        schematicsSum += Integer.parseInt(schematicsEntry.getFullNumberWithSurroundings());
                    }


                }
            }
        }

        return String.valueOf(schematicsSum);
    }

    @Override
    public String solvePart2() {
        List<String> lines = this.readFileLines();
        int schematicsSum = 0;
        // collect some additional info first
        List<List<SchematicsEntry>> schematicsEntries = lines.stream()
                .map((line) -> line.chars().mapToObj(c -> new SchematicsEntry((char) c)).toList())
                .toList();

        // initialize full numbers
        for (List<SchematicsEntry> entry : schematicsEntries) {
            for (int position = 0; position < entry.size(); position++) {
                SchematicsEntry schematicsEntry = entry.get(position);
                if (schematicsEntry.getSchematicsType().equals(SchematicsType.NUMBER)) {
                    if (!schematicsEntry.isNumberPart()) {
                        schematicsEntry.setNumberPart(true);
                        schematicsEntry.setFullNumber(entry, position);
                    } else {
                        schematicsEntry.setFullNumberWithSurroundings(entry.get(position - 1).getFullNumberWithSurroundings());
                    }
                }
            }
        }

        // find all schematicsentry with type asterics and search for surrounding numbers to multipy
        for(int rowNumber = 0; rowNumber < schematicsEntries.size(); rowNumber++) {
            for (int position = 0; position < schematicsEntries.get(rowNumber).size(); position++) {
                SchematicsEntry schematicsEntry = schematicsEntries.get(rowNumber).get(position);
                if (schematicsEntry.getSchematicsType().equals(SchematicsType.ASTERISK)) {
                    schematicsSum += schematicsEntry.getSurroundingNumbersMultiplication(rowNumber, position, schematicsEntries);
                }
            }
        }


        return String.valueOf(schematicsSum);
    }
}

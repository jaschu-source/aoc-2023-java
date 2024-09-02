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
                if(schematicsEntry.getSchematicsType().equals(SchematicsType.NUMBER) && !schematicsEntry.isAlreadyCounted()) {
                    schematicsEntry.setAlreadyCounted(true);
                    schematicsEntry.setFullNumber(schematicsEntries.get(rowNumber), position);

                    if (schematicsEntry.isSchematic(rowNumber, position, schematicsEntries)) {
                        schematicsSum += Integer.parseInt(schematicsEntry.getFullNumberWithSurroundings());
                    }


                }
            }
        }

        return String.valueOf(schematicsSum);
    }

    @Override
    public String solvePart2() {
        return null;
    }
}

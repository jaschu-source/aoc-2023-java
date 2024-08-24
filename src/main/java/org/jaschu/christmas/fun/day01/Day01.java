package org.jaschu.christmas.fun.day01;

import org.apache.commons.lang3.StringUtils;
import org.jaschu.christmas.fun.common.AbstractPuzzle;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day01 extends AbstractPuzzle {
    private static final Map<String, Integer> possibleNumbersMap = Map.of(
            "one", 1, "two", 2, "three", 3, "four", 4, "five", 5,
            "six", 6, "seven", 7, "eight", 8, "nine", 9
    );

    public Day01(String file) {
        super(file);
    }

    @Override
    public String solvePart1() {
        AtomicInteger calibrationNumberSum = new AtomicInteger();
        List<String> lines = this.readFileLines();
        lines.forEach(line -> {
            List<Character> characters = line.chars().mapToObj(c -> (char) c).toList();
            List<Character> numbers = characters.stream().filter(character -> StringUtils.isNumeric(Character.toString(character))).toList();
            calibrationNumberSum.addAndGet(Integer.parseInt(numbers.getFirst() + Character.toString(numbers.getLast())));
        });
        return String.valueOf(calibrationNumberSum.get());
    }

    @Override
    public String solvePart2() {
        List<String> lines = this.readFileLines();
        AtomicInteger calibrationNumberSum = new AtomicInteger();

        lines.forEach(line -> calibrationNumberSum.addAndGet(addCalibrationValuesWithPossibleNumbers(line)));

        return String.valueOf(calibrationNumberSum.get());
    }

    private int addCalibrationValuesWithPossibleNumbers(String line) {
        CalibrationNumber calibrationNumberFirst = new CalibrationNumber("0", 9999);
        CalibrationNumber calibrationNumberLast = new CalibrationNumber("0", 0);

        possibleNumbersMap.forEach((key, number) -> {

            // number means [1-9]; string means the string representation of the numbers
            int numberIndex = line.indexOf(number.toString());
            int stringIndex = line.indexOf(key);

            boolean numberIndexCheck = numberIndex >=0 && numberIndex <= calibrationNumberFirst.position;
            boolean stringIndexCheck = stringIndex >= 0 && stringIndex <= calibrationNumberFirst.position;

            if( numberIndexCheck || stringIndexCheck) {
                if (numberIndex >= 0 && stringIndex >= 0) {
                    calibrationNumberFirst.position = Math.min(numberIndex, stringIndex);
                } else {
                    calibrationNumberFirst.position = numberIndex >= 0 ? numberIndex : stringIndex;
                }
                calibrationNumberFirst.number = number.toString();
            }

            numberIndex = line.lastIndexOf(number.toString());
            stringIndex = line.lastIndexOf(key);

            if(numberIndex >= calibrationNumberLast.position || stringIndex >= calibrationNumberLast.position) {
                calibrationNumberLast.position = Math.max(numberIndex, stringIndex);
                calibrationNumberLast.number = number.toString();
            }
        });

        return Integer.parseInt(calibrationNumberFirst.number + calibrationNumberLast.number);
    }
}

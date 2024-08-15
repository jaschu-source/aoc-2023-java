package org.jaschu.christmas.fun.day01;

import org.jaschu.christmas.fun.common.AbstractPuzzle;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

public class Day01 extends AbstractPuzzle {
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
        return null;
    }
}

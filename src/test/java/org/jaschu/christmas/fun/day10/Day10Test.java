package org.jaschu.christmas.fun.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {
    Day10 day10;

    @Test
    void solvePart1Example1() {
        day10 = new Day10("10-example-1.txt");
        assertThat(day10.solvePart1()).isEqualTo("4");
    }

    @Test
    void solvePart1Example2() {
        day10 = new Day10("10-example-2.txt");
        assertThat(day10.solvePart1()).isEqualTo("8");
    }

    @Test
    void solvePart2() {
    }
}
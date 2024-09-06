package org.jaschu.christmas.fun.day03;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day03Test {

    @Test
    void solvePart1() {
        Day03 day03 = new Day03("03-example.txt");
        assertThat(day03.solvePart1()).isEqualTo(String.valueOf(4361));
    }

    @Test
    void solvePart2() {
        Day03 day03 = new Day03("03-example.txt");
        assertThat(day03.solvePart2()).isEqualTo(String.valueOf(467835));
    }
}
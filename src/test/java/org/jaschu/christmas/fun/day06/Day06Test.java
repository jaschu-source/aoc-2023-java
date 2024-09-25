package org.jaschu.christmas.fun.day06;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day06Test {
    @Test
    void solvePart1() {
        Day06 day06 = new Day06("06-example.txt");
        assertThat(day06.solvePart1()).isEqualTo("288");
    }

    @Test
    void solvePart2() {
        Day06 day06 = new Day06("06-example.txt");
        assertThat(day06.solvePart2()).isEqualTo("71503");
    }
}
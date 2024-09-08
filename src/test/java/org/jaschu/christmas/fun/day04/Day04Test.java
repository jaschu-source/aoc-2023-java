package org.jaschu.christmas.fun.day04;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day04Test {
    @Test
    void solvePart1() {
        Day04 day04 = new Day04("04-example.txt");
        assertThat(day04.solvePart1()).isEqualTo("13");
    }

    @Test
    void solvePart2() {
    }
}
package org.jaschu.christmas.fun.day05;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day05Test {

    @Test
    void solvePart1() {
        Day05 day05 = new Day05("05-example.txt");
        assertThat(day05.solvePart1()).isEqualTo("35");
    }

    @Test
    void solvePart2() {
        Day05 day05 = new Day05("05-example.txt");
    }
}
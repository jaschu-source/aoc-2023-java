package org.jaschu.christmas.fun.day09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day09Test {
    Day09 day09;

    @BeforeEach
    void setUp() {
        day09 = new Day09("09-example.txt");
    }

    @Test
    void solvePart1() {
        assertThat(day09.solvePart1()).isEqualTo("114");
    }

    @Test
    void solvePart2() {
        assertThat(day09.solvePart2()).isEqualTo("2");
    }
}
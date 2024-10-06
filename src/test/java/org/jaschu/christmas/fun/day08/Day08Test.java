package org.jaschu.christmas.fun.day08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day08Test {
    Day08 day08;
    Day08 day082;

    @BeforeEach
    void setUp() {
        day08 = new Day08("08-example-1.txt");
        day082 = new Day08("08-example-2.txt");
    }

    @Test
    void solvePart1Test1() {
        assertThat(day08.solvePart1()).isEqualTo("2");
    }

    @Test
    void solvePart1Test2() {
        assertThat(day082.solvePart1()).isEqualTo("6");
    }

    @Test
    void solvePart2() {
    }
}
package org.jaschu.christmas.fun.day07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day07Test {
    private Day07 day07;

    @BeforeEach
    void setUp() {
        day07 = new Day07("07-example.txt");
    }

    @Test
    void solvePart1() {
        assertThat(day07.solvePart1()).isEqualTo("6440");
    }

    @Test
    void solvePart2() {
        assertThat(day07.solvePart1()).isEqualTo("");
    }
}
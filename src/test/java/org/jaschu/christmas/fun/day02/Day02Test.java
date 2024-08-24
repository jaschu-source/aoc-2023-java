package org.jaschu.christmas.fun.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day02Test {

    @Test
    void solvePart1() {
        Day02 day02 = new Day02("02-example.txt", 12, 13, 14);
        assertThat(day02.solvePart1()).isEqualTo(String.valueOf(8));
    }

    @Test
    void solvePart2() {
        Day02 day02 = new Day02("02-example.txt", 12, 13, 14);
        assertThat(day02.solvePart2()).isEqualTo(String.valueOf(2286));
    }
}
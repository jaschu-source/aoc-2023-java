package org.jaschu.christmas.fun;

import org.jaschu.christmas.fun.day01.Day01;

public class Main {
    public static void main(String[] args) {
        System.out.println("-- Advent of Code 2023 --");
        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        System.out.println();
        System.out.println("-- Day 01: Calibrating Trebuchet --");

        Day01 day01 = new Day01("01.txt");
        System.out.println("Calibrating Sum 1: " + day01.solvePart1());
        System.out.println("Calibrating Sum 2: " + day01.solvePart2());
    }
}

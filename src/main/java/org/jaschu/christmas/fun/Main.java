package org.jaschu.christmas.fun;

import org.jaschu.christmas.fun.day01.Day01;
import org.jaschu.christmas.fun.day02.Day02;
import org.jaschu.christmas.fun.day03.Day03;
import org.jaschu.christmas.fun.day04.Day04;

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

        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        System.out.println();
        System.out.println("-- Day 02: Let's play a game of cubes --");
        Day02 day02 = new Day02("02.txt", 12, 13, 14);
        System.out.println("Possible Games Sum 1: " + day02.solvePart1());
        System.out.println("Possible Games Sum 2: " + day02.solvePart2());

        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        System.out.println();
        System.out.println("-- Day 03: Gondola Engineering --");
        Day03 day03 = new Day03("03.txt");
        System.out.println("Engine Schematics Number 1: " + day03.solvePart1());
        System.out.println("Engine Schematics Number 2: " + day03.solvePart2());

        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        System.out.println();
        System.out.println("-- Day 04: Winning the lottery --");
        Day04 day04 = new Day04("04.txt");
        System.out.println("Scratch Card Points: " + day04.solvePart1());
        System.out.println("Pile of Scratch Cards: " + day04.solvePart2());
    }
}

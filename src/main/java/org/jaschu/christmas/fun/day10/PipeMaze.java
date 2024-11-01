package org.jaschu.christmas.fun.day10;

import java.util.List;

public class PipeMaze {
    String[][] map;

    public PipeMaze(List<String> input) {
        // Initialize map based on the list size and string lengths
        map = new String[input.size()][];

        for (int i = 0; i < input.size(); i++) {
            String str = input.get(i);
            map[i] = new String[str.length()]; // Initialize the inner array for each string's length

            for (int j = 0; j < str.length(); j++) {
                map[i][j] = String.valueOf(str.charAt(j)); // Store each character as a String
            }
        }
    }

    public int searchStepsToFurthestPoint() {
        return 0;
    }
}

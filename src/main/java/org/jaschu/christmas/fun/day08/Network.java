package org.jaschu.christmas.fun.day08;

import java.util.HashMap;
import java.util.List;

public class Network {
    private String navigationInstructions;
    private final HashMap<String, String[]> nodes;
    private final HashMap<String, String> startEndMap;

    public Network(List<String> input) {
        nodes = new HashMap<>();
        startEndMap = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            if (i == 0) {
                navigationInstructions = input.get(i);
            } else if (i >= 2) {
                String line = input.get(i).replaceAll("\\s", "");
                String[] parts = line.split("=");
                String[] leftRight = parts[1].replaceAll("\\(", "").replaceAll("\\)", "").split(",");

                nodes.put(parts[0], leftRight);
                if (parts[0].endsWith("A")) startEndMap.put(parts[0], parts[0]);
            }
        }
    }

    // destination at ZZZ
    public int searchDestination() {
        int steps = 0;
        String currentNode = "AAA";
        String destination = "ZZZ";

        while (!currentNode.equals(destination)) {
            for (int i = 0; i < navigationInstructions.length(); i++) {
                String instruction = navigationInstructions.substring(i, i + 1);
                steps++;
                if (instruction.equals("R")) {
                    currentNode = nodes.get(currentNode)[1];
                } else {
                    currentNode = nodes.get(currentNode)[0];
                }
                if (currentNode.equals(destination)) break;
            }
        }
        return steps;
    }

    public long searchDestinationLikeAGhost() {
        long steps = 0;
        boolean allDestinationsFound = false;

        while (!allDestinationsFound) {
            for (int i = 0; i < navigationInstructions.length(); i++) {
                String instruction = navigationInstructions.substring(i, i + 1);
                steps++;
                startEndMap.entrySet().parallelStream().forEach((entry) -> startEndMap.put(entry.getKey(), instruction.equals("R") ? nodes.get(entry.getValue())[1] : nodes.get(entry.getValue())[0]));

                if (startEndMap.entrySet().stream().filter(entry -> entry.getValue().endsWith("Z")).count() == startEndMap.size()) {
                    allDestinationsFound = true;
                    break;
                }
            }
            System.out.println(steps);
        }
        return steps;
    }
}

package org.jaschu.christmas.fun.day08;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class Network {
    private String navigationInstructions;
    private String startNode;
    private final HashMap<String, String[]> nodes;

    public Network(List<String> input) {
        nodes = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            if (i == 0) {
                navigationInstructions = input.get(i);
            } else if (i >= 2) {
                String line = input.get(i).replaceAll("\\s", "");
                String[] parts = line.split("=");
                String[] leftRight = parts[1].replaceAll("\\(", "").replaceAll("\\)", "").split(",");

                nodes.put(parts[0], leftRight);
            }
        }
    }

    // destination at ZZZ
    public int searchDestination() {
        startNode = "AAA";
        String destination = "ZZZ";
        int steps = 0;
        String currentNode = startNode;

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
            System.out.println("Destination: " + destination);
            System.out.println("Current: " + currentNode);
            System.out.println("Steps: " + steps);
        }
        return steps;
    }
}

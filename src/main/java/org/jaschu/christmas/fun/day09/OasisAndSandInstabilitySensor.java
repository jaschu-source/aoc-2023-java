package org.jaschu.christmas.fun.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OasisAndSandInstabilitySensor {
    List<String[]> histories;

    public OasisAndSandInstabilitySensor(List<String> input) {
        histories = new ArrayList<>();
        input.forEach(line -> {
            histories.add(line.split(" "));
        });
    }

    public String analyzeHistories(boolean pastValue) {
        AtomicInteger analyzedValue = new AtomicInteger();
        histories.forEach(history -> {
            analyzedValue.set(analyzedValue.get() + (pastValue ? findPastValue(history) : findNextValue(history)));
        });
        return String.valueOf(analyzedValue.get());
    }

    public int findNextValue(String[] history) {
        if (Arrays.stream(history).allMatch("0"::equals)) {
            return 0;
        } else {
            String[] nextHistory = new String[history.length - 1];
            int nextHistoryIndex = 0;
            for (int i = 0; i < history.length - 1; i++) {
                nextHistory[nextHistoryIndex] = String.valueOf(Integer.parseInt(history[i + 1]) - Integer.parseInt(history[i]));
                nextHistoryIndex++;
            }
            return Integer.parseInt(history[history.length - 1]) + findNextValue(nextHistory);
        }
    }

    public int findPastValue(String[] history) {
        if (Arrays.stream(history).allMatch("0"::equals)) {
            return 0;
        } else {
            String[] nextHistory = new String[history.length - 1];
            int nextHistoryIndex = 0;
            for (int i = 0; i < history.length - 1; i++) {
                nextHistory[nextHistoryIndex] = String.valueOf(Integer.parseInt(history[i + 1]) - Integer.parseInt(history[i]));
                nextHistoryIndex++;
            }
            int result = Integer.parseInt(history[0]) - findNextValue(nextHistory);
            return Integer.parseInt(history[0]) - findNextValue(nextHistory);
        }
    }
}

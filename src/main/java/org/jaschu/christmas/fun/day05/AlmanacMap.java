package org.jaschu.christmas.fun.day05;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlmanacMap {
    /**
     * Rather than list every source number and its corresponding destination number one by one,
     * the maps describe entire ranges of numbers that can be converted. Each line within a map contains three numbers:
     * the destination range start, the source range start, and the range length.
     */
    String destinationRangeStart;
    String sourceRangeStart;
    String rangeLength;
}

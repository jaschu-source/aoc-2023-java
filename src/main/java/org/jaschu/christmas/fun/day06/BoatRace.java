package org.jaschu.christmas.fun.day06;

import lombok.Getter;

@Getter
public class BoatRace {
    private final int raceDuration;
    private final long recordDistanceInMillimeters;

    public BoatRace(int raceDuration, long recordDistanceInMillimeters) {
        this.raceDuration = raceDuration;
        this.recordDistanceInMillimeters = recordDistanceInMillimeters;
    }
}

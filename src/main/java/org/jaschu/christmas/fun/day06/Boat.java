package org.jaschu.christmas.fun.day06;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Boat {
    private final int increasedSpeedPerMillisecond;

    public Boat(int IncreasedSpeedPerMillisecond) {
        this.increasedSpeedPerMillisecond = IncreasedSpeedPerMillisecond;
    }

    public String getMultipliedNumberOfWaysToWin(List<BoatRace> boatRaces) {
        AtomicInteger numberOfWaysToWinMultiplied = new AtomicInteger();
        boatRaces.forEach(boatRace -> {
            int numberOfWaysToWin = 0;
            for (long i = 0; i <= boatRace.getRaceDuration(); i++) {
                if (increasedSpeedPerMillisecond * i * (boatRace.getRaceDuration() - i) > boatRace.getRecordDistanceInMillimeters()) {
                    numberOfWaysToWin++;
                }
            }
            if (numberOfWaysToWinMultiplied.get() == 0) {
                numberOfWaysToWinMultiplied.set(numberOfWaysToWin);
            } else {
                numberOfWaysToWinMultiplied.set(numberOfWaysToWinMultiplied.get() * numberOfWaysToWin);
            }
        });
        return String.valueOf(numberOfWaysToWinMultiplied.get());
    }
}

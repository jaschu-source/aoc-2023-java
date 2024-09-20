package org.jaschu.christmas.fun.day05;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @deprecated This was a nice idea, but there are too many numbers to store for a long time
 */
@Data
public class SmallAlmanac {
    private List<Long> seeds;
    private Map<Long, Long> seedToSoilMap;
    private Map<Long, Long> soilToFertilizerMap;
    private Map<Long, Long> fertilizerToWaterMap;
    private Map<Long, Long> waterToLightMap;
    private Map<Long, Long> lightToTemperatureMap;
    private Map<Long, Long> temperatureToHumidityMap;
    private Map<Long, Long> humidityToLocationMap;

    private int initialisationIndex; // only needed for centralized initialization process in constructor

    public SmallAlmanac(List<String> input) {
        initialisationIndex = 0;
        while (initialisationIndex < input.size()) {
            String[] almanacIdentifier = input.get(initialisationIndex).split(":");
            switch (almanacIdentifier[0]) {
                case AlmanacInfo.SEEDS -> setSeeds(almanacIdentifier[1]);
                case AlmanacInfo.SEED_TO_SOIL -> this.seedToSoilMap = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.SOIL_TO_FERTILIZER ->
                        this.soilToFertilizerMap = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.FERTILIZER_TO_WATER ->
                        this.fertilizerToWaterMap = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.WATER_TO_LIGHT -> this.waterToLightMap = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.LIGHT_TO_TEMPERATURE ->
                        this.lightToTemperatureMap = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.TEMPERATURE_TO_HUMIDITY ->
                        this.temperatureToHumidityMap = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.HUMIDITY_TO_LOCATION ->
                        this.humidityToLocationMap = generateAlmanacMapFromStringInput(input);
                default -> initialisationIndex++;
            }
        }
    }

    public long getClosestLocation() {
        final Long[] location = {null};
        seeds.forEach(seed -> {
            long soil = seedToSoilMap.getOrDefault(seed, seed);
            long fertilizer = soilToFertilizerMap.getOrDefault(soil, soil);
            long water = fertilizerToWaterMap.getOrDefault(fertilizer, fertilizer);
            long light = waterToLightMap.getOrDefault(water, water);
            long temperature = lightToTemperatureMap.getOrDefault(light, light);
            long humidity = temperatureToHumidityMap.getOrDefault(temperature, temperature);
            long newLocation = humidityToLocationMap.getOrDefault(humidity, humidity);
            if (location[0] == null || newLocation < location[0]) location[0] = newLocation;
        });
        return location[0];
    }

    private void setSeeds(String seeds) {
        this.seeds = Arrays.stream(seeds.substring(1).split(" ")).map(Long::parseLong).toList();
        initialisationIndex++;
    }

    /**
     * Rather than list every source number and its corresponding destination number one by one,
     * the maps describe entire ranges of numbers that can be converted. Each line within a map contains three numbers:
     * the destination range start, the source range start, and the range length.
     */
    private Map<Long, Long> generateAlmanacMapFromStringInput(List<String> input) {
        Map<Long, Long> almanacMap = new HashMap<>();
        initialisationIndex++;
        String mapLine = input.get(initialisationIndex);

        while (!mapLine.isEmpty() && initialisationIndex < input.size()) {
            String[] rangeNumbers = mapLine.split(" ");
            long destinationRangeStart = Long.parseLong(rangeNumbers[0]);
            long sourceRangeStart = Long.parseLong(rangeNumbers[1]);
            long rangeLenght = Long.parseLong(rangeNumbers[2]);

            for (long i = 0; i < rangeLenght; i++) {
                almanacMap.put(sourceRangeStart + i, destinationRangeStart + i);
            }

            initialisationIndex++;
            if (initialisationIndex < input.size()) mapLine = input.get(initialisationIndex);
        }
        return almanacMap;
    }
}

package org.jaschu.christmas.fun.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Almanac {
    private List<String> seeds;
    private List<AlmanacMap> seedsMaps;
    private List<AlmanacMap> seedToSoilMaps;
    private List<AlmanacMap> soilToFertilizerMaps;
    private List<AlmanacMap> fertilizerToWaterMaps;
    private List<AlmanacMap> waterToLightMaps;
    private List<AlmanacMap> lightToTemperatureMaps;
    private List<AlmanacMap> temperatureToHumidityMaps;
    private List<AlmanacMap> humidityToLocationMaps;

    private int initialisationIndex; // only needed for centralized initialization process in constructor

    public Almanac(List<String> input) {
        initialisationIndex = 0;
        while (initialisationIndex < input.size()) {
            String[] almanacIdentifier = input.get(initialisationIndex).split(":");
            switch (almanacIdentifier[0]) {
                case AlmanacInfo.SEEDS -> setSeeds(almanacIdentifier[1]);
                case AlmanacInfo.SEED_TO_SOIL -> this.seedToSoilMaps = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.SOIL_TO_FERTILIZER ->
                        this.soilToFertilizerMaps = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.FERTILIZER_TO_WATER ->
                        this.fertilizerToWaterMaps = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.WATER_TO_LIGHT -> this.waterToLightMaps = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.LIGHT_TO_TEMPERATURE ->
                        this.lightToTemperatureMaps = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.TEMPERATURE_TO_HUMIDITY ->
                        this.temperatureToHumidityMaps = generateAlmanacMapFromStringInput(input);
                case AlmanacInfo.HUMIDITY_TO_LOCATION ->
                        this.humidityToLocationMaps = generateAlmanacMapFromStringInput(input);
                default -> initialisationIndex++;
            }
        }
    }

    public String getClosestLocationFromSeedRange() {
        AtomicReference<String> closestLocation = new AtomicReference<>("");
        AtomicReference<String> newLocation = new AtomicReference<>("");
        this.seedsMaps.parallelStream().forEach(almanacMap -> {
            newLocation.set(getLocationFromMaps(almanacMap.sourceRangeStart));
            long seedRangeStart = Long.parseLong(almanacMap.sourceRangeStart);
            long seedRangeEnd = Long.parseLong(almanacMap.sourceRangeStart) + Long.parseLong(almanacMap.rangeLength) - 1;

            String expectedEndLocation = String.valueOf(Long.parseLong(newLocation.get()) + Long.parseLong(almanacMap.rangeLength) - 1);
            String rangeEndLocation = getLocationFromMaps(String.valueOf(seedRangeEnd));

            if (!expectedEndLocation.equals(rangeEndLocation)) {
                for (long i = seedRangeStart; i < seedRangeStart + Long.parseLong(almanacMap.rangeLength); i++) {
                    newLocation.set(getLocationFromMaps(String.valueOf(i)));
                    updateClosestLocation(closestLocation, newLocation);
                }
            } else {
                updateClosestLocation(closestLocation, newLocation);
            }
        });
        return closestLocation.get();

    }

    public String getClosestLocation() {
        AtomicReference<String> location = new AtomicReference<>("");
        seeds.forEach(seed -> {
            String newLocation = getLocationFromMaps(seed);
            if (location.get().isEmpty() || Long.parseLong(newLocation) < Long.parseLong(location.get()))
                location.set(newLocation);
        });
        return location.get();
    }

    private String getLocationFromMaps(String seed) {
        String soil = getValueFromMaps(seedToSoilMaps, seed);
        String fertilizer = getValueFromMaps(soilToFertilizerMaps, soil);
        String water = getValueFromMaps(fertilizerToWaterMaps, fertilizer);
        String light = getValueFromMaps(waterToLightMaps, water);
        String temperature = getValueFromMaps(lightToTemperatureMaps, light);
        String humidity = getValueFromMaps(temperatureToHumidityMaps, temperature);
        return getValueFromMaps(humidityToLocationMaps, humidity);
    }

    private String getValueFromMaps(List<AlmanacMap> almanacMaps, String key) {
        final long longKey = Long.parseLong(key);

        return almanacMaps.parallelStream()
                .filter(almanacMap -> {
                    long sourceRangeStart = Long.parseLong(almanacMap.sourceRangeStart);
                    long rangeLength = Long.parseLong(almanacMap.rangeLength);
                    return longKey >= sourceRangeStart && longKey < sourceRangeStart + rangeLength;
                })
                .findFirst()  // Only need the almanac that contains the key
                .map(almanacMap -> {
                    long sourceRangeStart = Long.parseLong(almanacMap.sourceRangeStart);
                    long destinationRangeStart = Long.parseLong(almanacMap.destinationRangeStart);
                    return destinationRangeStart + (longKey - sourceRangeStart);
                })
                .map(String::valueOf)
                .orElse(key);  // Return the original key if no match is found
    }


    private void setSeeds(String seeds) {
        this.seeds = Arrays.stream(seeds.substring(1).split(" ")).toList();
        this.seedsMaps = generateSeedsMaps();
        initialisationIndex++;
    }

    private List<AlmanacMap> generateAlmanacMapFromStringInput(List<String> input) {
        List<AlmanacMap> listOfMaps = new ArrayList<>();
        initialisationIndex++;
        String mapLine = input.get(initialisationIndex);

        while (!mapLine.isEmpty() && initialisationIndex < input.size()) {
            String[] rangeNumbers = mapLine.split(" ");

            listOfMaps.add(new AlmanacMap(rangeNumbers[0], rangeNumbers[1], rangeNumbers[2]));

            initialisationIndex++;
            if (initialisationIndex < input.size()) mapLine = input.get(initialisationIndex);
        }
        return listOfMaps;
    }

    private List<AlmanacMap> generateSeedsMaps() {
        List<AlmanacMap> almanacMaps = new ArrayList<>();
        if (this.seeds.size() % 2 != 0)
            throw new IllegalArgumentException("Incorrect input format for generating of seed range.");
        int i = 0;
        while (i < this.seeds.size()) {
            almanacMaps.add(new AlmanacMap(this.seeds.get(i), this.seeds.get(i), this.seeds.get(i + 1)));
            i += 2;
        }
        return almanacMaps;
    }

    private static void updateClosestLocation(AtomicReference<String> closestLocation, AtomicReference<String> newLocation) {
        closestLocation.accumulateAndGet(newLocation.get(), (current, update) -> {
            if (current.isEmpty() || Long.parseLong(update) < Long.parseLong(current)) {
                return update;
            }
            return current;
        });
    }

}

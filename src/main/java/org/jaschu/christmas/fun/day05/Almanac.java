package org.jaschu.christmas.fun.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Almanac {
    private List<String> seeds;
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

    public String getClosestLocation() {
        AtomicReference<String> location = new AtomicReference<>("");
        seeds.forEach(seed -> {
            String soil = getValueFromMaps(seedToSoilMaps, seed);
            String fertilizer = getValueFromMaps(soilToFertilizerMaps, soil);
            String water = getValueFromMaps(fertilizerToWaterMaps, fertilizer);
            String light = getValueFromMaps(waterToLightMaps, water);
            String temperature = getValueFromMaps(lightToTemperatureMaps, light);
            String humidity = getValueFromMaps(temperatureToHumidityMaps, temperature);
            String newLocation = getValueFromMaps(humidityToLocationMaps, humidity);
            if (location.get().isEmpty() || Long.parseLong(newLocation) < Long.parseLong(location.get()))
                location.set(newLocation);
        });
        return location.get();
    }

    private String getValueFromMaps(List<AlmanacMap> almanacMaps, String key) {
        final long longKey = Long.parseLong(key);
        AtomicLong value = new AtomicLong(Long.parseLong(key));
        almanacMaps.forEach(almanacMap -> {
            long sourceRangeStart = Long.parseLong(almanacMap.sourceRangeStart);
            long destinationRangeStart = Long.parseLong(almanacMap.destinationRangeStart);
            long rangeLenght = Long.parseLong(almanacMap.rangeLength);
            if (longKey > sourceRangeStart && longKey < sourceRangeStart + rangeLenght) {
                value.set(destinationRangeStart + (longKey - sourceRangeStart));
            }
        });
        return String.valueOf(value.get());
    }

    private void setSeeds(String seeds) {
        this.seeds = Arrays.stream(seeds.substring(1).split(" ")).toList();
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

}

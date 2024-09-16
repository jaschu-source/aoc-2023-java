package org.jaschu.christmas.fun.day05;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Almanac {
    private List<Integer> seeds;
    private Map<Integer, Integer> seedToSoilMap;
    private Map<Integer, Integer> soilToFertilizerMap;
    private Map<Integer, Integer> fertilizerToWaterMap;
    private Map<Integer, Integer> waterToLightMap;
    private Map<Integer, Integer> lightToTemperatureMap;
    private Map<Integer, Integer> temperatureToHumidityMap;
    private Map<Integer, Integer> humidityToLocationMap;

    private int initialisationIndex = 0; // only needed for centralized initialization process in constructor

    public Almanac(List<String> input) {
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

    public void setSeeds(String seeds) {
        this.seeds = new ArrayList<>();
        initialisationIndex++;
    }

    private Map<Integer, Integer> generateAlmanacMapFromStringInput(List<String> input) {
        initialisationIndex++;
        return new HashMap<>();
    }
}

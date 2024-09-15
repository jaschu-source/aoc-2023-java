package org.jaschu.christmas.fun.day05;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Almanac {
    private List<Integer> seeds;
    private Map<Integer, Integer> SeedToSoilMap;
    private Map<Integer, Integer> SoilToFertilizerMap;
    private Map<Integer, Integer> FertilizerToWaterMap;
    private Map<Integer, Integer> WaterToLightMap;
    private Map<Integer, Integer> LightToTemperatureMap;
    private Map<Integer, Integer> TemperatureToHumidityMap;
    private Map<Integer, Integer> HumidityToLocationMap;

    public Almanac(List<String> input) {

    }
}

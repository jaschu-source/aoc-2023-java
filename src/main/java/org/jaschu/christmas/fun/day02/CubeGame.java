package org.jaschu.christmas.fun.day02;

import lombok.Builder;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Builder
public class CubeGame {
    private int identifyingNumber = 0;
    private int redCubes = 0;
    private int greenCubes = 0;
    private int blueCubes = 0;

    /**
     *
     * @param gameString: A string depicting a game with multiple runs
     *                  in format "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
     */
    public static CubeGame generateCubeGame(String gameString) {
        String identifyingNumber = null;
        int maxRed = 0;
        int maxGreen = 0;
        int maxBlue = 0;

        // Regex to extract the game number
        Pattern gamePattern = Pattern.compile("Game \\d+");
        Matcher gameMatcher = gamePattern.matcher(gameString);

        if (gameMatcher.find()) {
            identifyingNumber = gameMatcher.group().replace("Game ", "");
        }

        // remove game number from string & split into games
        String[] gameStrings = gameString.split(":")[1].split(";");

        for(String game: gameStrings) {
            // Regex to match color-value pairs
            Pattern colorPattern = Pattern.compile("(\\d+)\\s*(red|green|blue)");
            Matcher colorMatcher = colorPattern.matcher(game);

            // Iterate through all color-value pairs
            while (colorMatcher.find()) {
                int value = Integer.parseInt(colorMatcher.group(1));
                String color = colorMatcher.group(2);

                switch (color) {
                    case "red":
                        maxRed = Math.max(maxRed, value);
                        break;
                    case "green":
                        maxGreen = Math.max(maxGreen, value);
                        break;
                    case "blue":
                        maxBlue = Math.max(maxBlue, value);
                        break;
                }
            }
        }

        return CubeGame.builder().identifyingNumber(Integer.parseInt(identifyingNumber)).redCubes(maxRed).greenCubes(maxGreen).blueCubes(maxBlue).build();
    }

    public boolean isPossibleWithCubes(int red, int green, int blue){
        return red >= this.redCubes && green >= this.greenCubes && blue >= this.blueCubes;
    }
}

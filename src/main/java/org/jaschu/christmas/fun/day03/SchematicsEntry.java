package org.jaschu.christmas.fun.day03;

import lombok.Data;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class SchematicsEntry {
    private Character characterValue;
    private SchematicsType schematicsType;

    // only necessary if schematicsType is NUMBER
    private boolean numberPart = false; // already checked, if part of a bigger number
    private String fullNumberWithSurroundings;

    private boolean alreadyCheckedIfPartOfSchematics = false; // if true, already checked if part of the schematics needed for repair
    private boolean isSchematic = false;

    public SchematicsEntry(Character characterValue) {
        this.characterValue = characterValue;
        this.fullNumberWithSurroundings = String.valueOf(characterValue);
        Pattern numberPattern = Pattern.compile("\\d");
        Matcher numberMatcher = numberPattern.matcher(characterValue.toString());
        if(characterValue.equals('.')) {
            this.schematicsType = SchematicsType.DOT;
        } else if(characterValue.equals('*')) {
            this.schematicsType = SchematicsType.ASTERISK;
        }else if(numberMatcher.matches()) {
            this.schematicsType = SchematicsType.NUMBER;
        } else {
            this.schematicsType = SchematicsType.SYMBOL;
        }
    }

    /**
     *
     * @param schematicsEntries: list to search for full number
     * @param startPosition: position the number starts on
     */
    public void setFullNumber(List<SchematicsEntry> schematicsEntries, int startPosition) {
        searchFullNumber(schematicsEntries, this, startPosition);
    }

    /**
     *
     * @param schematicsEntries: list to search for full number
     * @param entry: position the number starts on
     * @param startPosition: position the number starts on
     */
    private static void searchFullNumber(List<SchematicsEntry> schematicsEntries, SchematicsEntry entry, int startPosition) {
        int entryPosition = startPosition + 1;
        if(startPosition < schematicsEntries.size() && entryPosition < schematicsEntries.size()) {
            SchematicsEntry followingEntry = schematicsEntries.get(entryPosition);
            if (followingEntry.getSchematicsType().equals(SchematicsType.NUMBER)) {
                entry.fullNumberWithSurroundings = entry.getFullNumberWithSurroundings() + followingEntry.getCharacterValue();
                followingEntry.setNumberPart(true);
                searchFullNumber(schematicsEntries, entry, startPosition + 1);
            }
        }
    }

    /**
     *
     * Method to determine if a schematic is part of the schematics that are needed for repair
     * @param validTypes: valid surrounding schematic types that imply a schematic
     */
    public void setSchematic(int rowNumber, int schemaPosition, List<List<SchematicsEntry>> schematicsEntries, List<SchematicsType> validTypes) {
        //determine surrounding positions
        int numberEnd = schemaPosition + this.getFullNumberWithSurroundings().length() - 1;

        boolean schematic = false;

        for(int currentRow = rowNumber - 1; currentRow <= rowNumber +1; currentRow++) {
            for(int position = schemaPosition - 1; position <= numberEnd + 1; position++) {
                if(schematicsEntries.size() > currentRow
                        && currentRow >= 0
                        && position >= 0
                        && schematicsEntries.get(currentRow).size() > position
                        && validTypes.contains(schematicsEntries.get(currentRow).get(position).schematicsType)
                ) {
                    schematic = true;
                    break;
                }
            }
        }

        this.isSchematic = schematic;
    }

    public int getSurroundingNumbersMultiplication(int rowNumber, int schemaPosition, List<List<SchematicsEntry>> schematicsEntries) {
        int numberMultiplication = 1;
        int numberCount = 0;
        for(int currentRow = rowNumber - 1; currentRow <= rowNumber +1; currentRow++) {
            for (int position = schemaPosition - 1; position <= schemaPosition + 1; position++) {
                if(schematicsEntries.size() > currentRow
                        && currentRow >= 0
                        && position >= 0
                        && schematicsEntries.get(currentRow).size() > position
                        && schematicsEntries.get(currentRow).get(position).schematicsType.equals(SchematicsType.NUMBER)
                        && !schematicsEntries.get(currentRow).get(position).alreadyCheckedIfPartOfSchematics
                ) {
                    numberCount++;
                    numberMultiplication *= Integer.parseInt(schematicsEntries.get(currentRow).get(position).getFullNumberWithSurroundings());

                    // because we know max character count of full number is max 3 its fine to only check 2 following
                    SchematicsEntry followingEntry1 = schematicsEntries.get(currentRow).get(position + 1);

                    if (followingEntry1 != null && followingEntry1.schematicsType.equals(SchematicsType.NUMBER)) {
                        followingEntry1.alreadyCheckedIfPartOfSchematics = true;
                        // only check for second entry if first entry was number to avoid marking adjacent numbers
                        SchematicsEntry followingEntry2 = schematicsEntries.get(currentRow).get(position + 2);
                        if (followingEntry2 != null && followingEntry2.schematicsType.equals(SchematicsType.NUMBER))
                            followingEntry2.alreadyCheckedIfPartOfSchematics = true;
                    }
                }
            }
        }

        // only * that are surrounded by 2 numbers count
        return numberCount == 2 ? numberMultiplication : 0;
    }
}

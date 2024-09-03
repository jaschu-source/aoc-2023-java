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
    private boolean alreadyCounted = false;
    private String fullNumberWithSurroundings;

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
                followingEntry.setAlreadyCounted(true);
                searchFullNumber(schematicsEntries, entry, startPosition + 1);
            }
        }
    }

    public boolean isSchematicPartOne(int rowNumber, int schemaPosition, List<List<SchematicsEntry>> schematicsEntries) {
        //determine surrounding positions
        int numberEnd = schemaPosition + this.getFullNumberWithSurroundings().length() - 1;

        boolean schematic = false;

        for(int currentRow = rowNumber - 1; currentRow <= rowNumber +1; currentRow++) {
            for(int position = schemaPosition - 1; position <= numberEnd + 1; position++) {
                if(schematicsEntries.size() > currentRow
                        && currentRow >= 0
                        && position >= 0
                        && schematicsEntries.get(currentRow).size() > position
                        && (schematicsEntries.get(currentRow).get(position).schematicsType.equals(SchematicsType.SYMBOL)
                        || schematicsEntries.get(currentRow).get(position).schematicsType.equals(SchematicsType.ASTERISK))
                ) {
                    schematic = true;
                    break;
                }
            }
        }
        return schematic;
    }
}

package com.harleyoconnor.rockpaperscissors;

/**
 * @author Harley O'Connor
 */
public enum SelectionType {
    ROCK("scissors"), PAPER("rock"), SCISSORS("paper");

    private final String beats;

    SelectionType (final String beats) {
        this.beats = beats.toUpperCase();
    }

    public boolean beats (final SelectionType selectionType) {
        final SelectionType selectionTypeItBeats = SelectionType.valueOf(this.beats);
        return selectionType == selectionTypeItBeats;
    }

}

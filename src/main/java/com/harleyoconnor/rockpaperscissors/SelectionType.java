package com.harleyoconnor.rockpaperscissors;

/**
 * @author Harley O'Connor
 */
public enum SelectionType {
    ROCK(Constants.SCISSORS), PAPER(Constants.ROCK), SCISSORS(Constants.PAPER);

    private final String beats;

    SelectionType (final String beats) {
        this.beats = beats;
    }

    public boolean beats (final SelectionType selectionType) {
        final SelectionType selectionTypeItBeats = SelectionType.valueOf(this.beats);
        return selectionType == selectionTypeItBeats;
    }

}

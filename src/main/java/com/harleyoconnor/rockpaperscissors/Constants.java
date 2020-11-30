package com.harleyoconnor.rockpaperscissors;

/**
 * @author Harley O'Connor
 */
public final class Constants {

    // Constants for the enum SelectionType, since you can't forward reference an undefined enum in the constructor.
    public static final String ROCK = "ROCK";
    public static final String PAPER = "PAPER";
    public static final String SCISSORS = "SCISSORS";

    // Font strings.
    public static final String ARIAL = "arial";

    // File prefix.
    public static final String FILE_PREFIX = "file:";

    // Sound file names.
    public static final String CORRECT_SOUND = "correct";
    public static final String INCORRECT_SOUND = "incorrect";

}

package by.gartsmanovich.java_web_dev.playroom.controller.command;

public enum CommandName {

    /**
     * The singleton instance for the play room creation command.
     * This has the numeric value of {@code 1}.
     */
    CREATE_PLAYROOM,

    /**
     * The singleton instance for the add new toy command.
     * This has the numeric value of {@code 2}.
     */
    ADD_TOY,

    /**
     * The singleton instance for the update toy by ID command.
     * This has the numeric value of {@code 3}.
     */
    UPDATE_TOY,

    /**
     * The singleton instance for the remove toy by ID command.
     * This has the numeric value of {@code 4}.
     */
    REMOVE_TOY,

    /**
     * The singleton instance for the find toy by ID command.
     * This has the numeric value of {@code 5}.
     */
    FIND_TOY_BY_ID,

    /**
     * The singleton instance for the find toy by title command.
     * This has the numeric value of {@code 6}.
     */
    FIND_TOY_BY_TITLE,

    /**
     * The singleton instance for the find toys by first title letter command.
     * This has the numeric value of {@code 7}.
     */
    FIND_TOYS_BY_FIRST_TITLE_LETTER,

    /**
     * The singleton instance for the find toys by range ID command.
     * This has the numeric value of {@code 8}.
     */
    FIND_TOYS_BY_RANGE_ID,

    /**
     * The singleton instance for the sort toys by age command.
     * This has the numeric value of {@code 9}.
     */
    SORT_BY_AGE,

    /**
     * The singleton instance for the sort toys by color and price command.
     * This has the numeric value of {@code 10}.
     */
    SORT_BY_COLOR_AND_PRICE,

    /**
     * The singleton instance for the exit command.
     * This has the numeric value of {@code 11}.
     */
    EXIT,

    /**
     * The singleton instance for the remove toy by ID command.
     * This has the numeric value of {@code 12}.
     */
    PRINT_ALL,

    /**
     The singleton instance for the wrong request command.
     * This has the numeric value of {@code 13}.
     */
    WRONG_REQUEST
}

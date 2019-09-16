package by.gartsmanovich.java_web_dev.playroom.controller.command;

public enum CommandName {
    /**
     * The singleton instance for the add new toy command.
     * This has the numeric value of {@code 1}.
     */
    ADD_NEW_TOY,

    /**
     * The singleton instance for the delete toy by ID command.
     * This has the numeric value of {@code 2}.
     */
    DELETE_TOY_BY_ID,

    /**
     * The singleton instance for the find toy by ID command.
     * This has the numeric value of {@code 3}.
     */
    FIND_TOY_BY_ID,

    /**
     * The singleton instance for the find toy by title command.
     * This has the numeric value of {@code 4}.
     */
    FIND_TOY_BY_TITLE,

    /**
     * The singleton instance for the find toys by first title letter command.
     * This has the numeric value of {@code 5}.
     */
    FIND_TOYS_BY_FIRST_TITLE_LETTER,

    /**
     * The singleton instance for the find toys by range ID command.
     * This has the numeric value of {@code 6}.
     */
    FIND_TOYS_BY_RANGE_ID,

    /**
     * The singleton instance for the sort toys by age command.
     * This has the numeric value of {@code 7}.
     */
    SORT_BY_AGE,

    /**
     * The singleton instance for the sort toys by color and price command.
     * This has the numeric value of {@code 8}.
     */
    SORT_BY_COLOR_AND_PRICE,

    /**
     The singleton instance for the wrong request command.
     * This has the numeric value of {@code 9}.
     */
    WRONG_REQUEST
}

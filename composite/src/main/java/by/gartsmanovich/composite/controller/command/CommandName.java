package by.gartsmanovich.composite.controller.command;

/**
 * Enum-class represents the list of commands that exists in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public enum CommandName {

    /**
     * The singleton instance for the composite creation command.
     * This has the numeric value of {@code 0}.
     */
    CREATE_COMPOSITE,

    /**
     * The singleton instance for the sort paragraphs command.
     * This has the numeric value of {@code 1}.
     */
    SORT_PARAGRAPHS,

    /**
     * The singleton instance for the sort words command.
     * This has the numeric value of {@code 2}.
     */
    SORT_WORDS,

    /**
     * The singleton instance for the sort lexemes command.
     * This has the numeric value of {@code 3}.
     */
    SORT_LEXEMES,

    /**
     * The singleton instance for the save composite command.
     * This has the numeric value of {@code 4}.
     */
    SAVE_COMPOSITE,

    /**
     * The singleton instance for the exit command.
     * This has the numeric value of {@code 5}.
     */
    EXIT,

    /**
     The singleton instance for the wrong request command.
     * This has the numeric value of {@code 6}.
     */
    WRONG_REQUEST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

package by.gartsmanovich.webparsing.controller.command;

/**
 * Enum-class represents the list of commands that exists in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public enum CommandName {

    /**
     * The singleton instance for the DOM command.
     * This has the numeric value of {@code 0}.
     */
    DOM,

    /**
     * The singleton instance for the SAX command.
     * This has the numeric value of {@code 1}.
     */
    SAX,

    /**
     * The singleton instance for the StAX command.
     * This has the numeric value of {@code 2}.
     */
    STAX,

    /**
     The singleton instance for the wrong request command.
     * This has the numeric value of {@code 3}.
     */
    WRONG_REQUEST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

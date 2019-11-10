package by.gartsmanovich.hitcher.controller.command;

/**
 * Enum-class represents the list of commands that exists in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public enum CommandName {

    /**
     * The singleton instance for the builder command.
     * This has the numeric value of {@code 0}.
     */
    BUILDER;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

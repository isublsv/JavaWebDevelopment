package by.gartsmanovich.hitcher.action;

import by.gartsmanovich.hitcher.action.impl.BanActionCommand;
import by.gartsmanovich.hitcher.action.impl.LoginActionCommand;
import by.gartsmanovich.hitcher.action.impl.LogoutActionCommand;
import by.gartsmanovich.hitcher.action.impl.RegisterActionCommand;

/**
 * Enum-class represents the list of commands that exists in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public enum CommandName {

    /**
     * The singleton instance for the login command.
     * This has the numeric value of {@code 0}.
     */
    LOGIN(new LoginActionCommand()),

    /**
     * The singleton instance for the logout command.
     * This has the numeric value of {@code 1}.
     */
    LOGOUT(new LogoutActionCommand()),

    /**
     * The singleton instance for the register command.
     * This has the numeric value of {@code 2}.
     */
    REGISTER(new RegisterActionCommand()),

    /**
     * The singleton instance for the ban command.
     * This has the numeric value of {@code 3}.
     */
    BAN(new BanActionCommand());

    /**
     * Represents Action Command.
     */
    private ActionCommand command;

    /**
     * Constructs CommandName instance with provided Action command.
     *
     * @param commandValue the provided Action Command.
     */
    CommandName(final ActionCommand commandValue) {
        this.command = commandValue;
    }

    /**
     * Gets Action command.
     *
     * @return the Action command instance.
     */
    public ActionCommand getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

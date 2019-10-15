package by.gartsmanovich.composite.controller.command.impl;

import by.gartsmanovich.composite.controller.command.Command;
import by.gartsmanovich.composite.controller.command.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describes the wrong request command that inform user about incorrect
 * command invocation.
 *
 * @author Dmitry Gartsmanovich
 */
public class WrongRequestCommand implements Command {

    /**
     * The logger for WrongRequestCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            WrongRequestCommand.class);

    /**
     * Indicates that there was received wrong request and command was not
     * found.
     *
     * @param request the provided string for processing.
     * @return the string value for the given request.
     */
    @Override
    public String execute(final String request) {
        LOGGER.debug("The command not found or wrong!");
        return MessageManager.getProperty("message.wrong.request");
    }
}

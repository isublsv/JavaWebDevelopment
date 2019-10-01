package by.gartsmanovich.javawebdev.matrix.controller.command.impl;

import by.gartsmanovich.javawebdev.matrix.controller.command.Command;
import by.gartsmanovich.javawebdev.matrix.controller.command.manager
        .MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WrongRequestCommand implements Command {

    /**
     * The logger for WrongRequest class.
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

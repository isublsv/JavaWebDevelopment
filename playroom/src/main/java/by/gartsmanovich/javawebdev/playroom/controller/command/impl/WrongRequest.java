package by.gartsmanovich.javawebdev.playroom.controller.command.impl;

import by.gartsmanovich.javawebdev.playroom.controller.command.Command;
import by.gartsmanovich.javawebdev.playroom.controller.command.manager
        .MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WrongRequest implements Command {

    /**
     * The logger for WrongRequest class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(WrongRequest.class);

    /**
     * Indicates that there was received wrong request and command was not
     * found.
     *
     * @param request the provided string for processing.
     * @return the string value for the given request.
     */
    @Override
    public String execute(final String request) {
        LOGGER.trace("The command not found or wrong!");
        return MessageManager.getProperty("message.wrong.request");
    }
}

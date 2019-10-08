package by.gartsmanovich.javawebdev.composite.controller.command.impl;

import by.gartsmanovich.javawebdev.composite.controller.command.Command;
import by.gartsmanovich.javawebdev.composite.controller.command.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describes the exit command and used to inform the user about
 * successful
 * stop of the application.
 *
 * @author Dmitry Gartsmanovich
 */
public class ExitCommand implements Command {

    /**
     * The logger for ExitCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ExitCommand.class);

    /**
     * The main method that changes its behavior depends on request parameter.
     *
     * @param request the provided string for processing.
     * @return a result string value of the provided request.
     */
    @Override
    public String execute(final String request) {
        LOGGER.debug("Exit from the application!");
        return MessageManager.getProperty("message.exit.correct");
    }
}

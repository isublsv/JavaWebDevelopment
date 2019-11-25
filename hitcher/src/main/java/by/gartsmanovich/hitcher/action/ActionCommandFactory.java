package by.gartsmanovich.hitcher.action;

import by.gartsmanovich.hitcher.action.impl.EmptyActionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class is used to define the type of provided command from request.
 *
 * @author Dmitry Gartsmanovich
 */
public final class ActionCommandFactory {

    /**
     * The logger for ActionCommandFactory class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(ActionCommandFactory.class);

    /**
     * Defines the command using request attributes.
     *
     * @param request the provide request information for HTTP servlets.
     * @return the appropriate command instance.
     */
    public ActionCommand defineCommand(final HttpServletRequest request) {
        ActionCommand current = new EmptyActionCommand();

        String command = request.getParameter("command");
        if (command == null || command.isEmpty()) {
            return current;
        }
        try {
            CommandName commandName = CommandName.valueOf(
                    command.toUpperCase());
            current = commandName.getCommand();
        } catch (IllegalArgumentException e) {
            String message = "Command \"" + current + "\" not found.";
            LOGGER.warn(message);
        }

        return current;
    }
}

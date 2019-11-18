package by.gartsmanovich.hitcher.controller.command;

import by.gartsmanovich.hitcher.controller.command.impl.BuilderCommand;
import by.gartsmanovich.hitcher.controller.command.impl.EmptyCommand;
import by.gartsmanovich.hitcher.controller.command.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Class is used to store and determine the different kind of commands.
 *
 * @author Dmitry Gartsmanovich
 */
public class CommandFactory {

    /**
     * Defines the command using request attributes.
     *
     * @param request the provide request information for HTTP servlets.
     * @return the appropriate command instance.
     */
    public Command defineCommand(final HttpServletRequest request) {
        Command current = new EmptyCommand();

        String command = request.getParameter("command");
        if (command == null || command.isEmpty()) {
            return current;
        }

        CommandName commandName = CommandName.valueOf(command.toUpperCase());
        if (commandName == CommandName.BUILDER) {
            current = new BuilderCommand();
        } else {
            request.setAttribute("wrongCommand",
                                 command + MessageManager.getProperty(
                                         "message.wrong.command"));
        }
        return current;
    }
}

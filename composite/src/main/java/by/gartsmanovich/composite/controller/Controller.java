package by.gartsmanovich.composite.controller;

import by.gartsmanovich.composite.controller.command.Command;
import by.gartsmanovich.composite.controller.command.CommandProvider;

/**
 * Class represents the Controller layer of the application. Created to manage
 * requests from user, invoke the commands and return the results from Service
 * layer ot the application.
 *
 * @author Dmitry Gartsmanovich
 */
public class Controller {

    /**
     * The default delimiter.
     */
    private static final String DEL = " ";
    /**
     * The class gives an access to supported commands.
     */
    private final CommandProvider provider = new CommandProvider();

    /**
     * Returns the result string value of executed command. The method takes
     * first token before the {@link Controller#DEL} if exists and
     * get an appropriate command from {@link Controller#provider} repository.
     *
     * @param request the initial request that contains the data
     *                for processing.
     * @return the result response of the found command.
     */
    public String executeTask(final String request) {
        String commandName;
        Command executionCommand;
        String response;
        String params = "";

        if (request.contains(DEL)) {
            commandName = request.substring(0, request.indexOf(DEL));
            params = request.substring(request.indexOf(DEL) + 1).toLowerCase();
        } else {
            commandName = request;
        }

        executionCommand = provider.getCommand(commandName);
        response = executionCommand.execute(params);

        return response;
    }
}

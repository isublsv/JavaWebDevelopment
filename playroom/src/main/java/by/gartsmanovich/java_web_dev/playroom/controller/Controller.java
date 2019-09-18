package by.gartsmanovich.java_web_dev.playroom.controller;

import by.gartsmanovich.java_web_dev.playroom.controller.command.Command;
import by.gartsmanovich.java_web_dev.playroom.controller.command
        .CommandProvider;
import by.gartsmanovich.java_web_dev.playroom.controller.command.manager
        .MessageManager;

public class Controller {

    /**
     * The class gives an access to supported commands.
     */
    private final CommandProvider provider = new CommandProvider();

    /**
     * The value of delimiter.
     */
    private static final char PARAM_DELIMITER = ' ';

    /**
     * Returns the result string value of executed command. The method takes
     * first token before the {@link Controller#PARAM_DELIMITER} and get an
     * appropriate command from {@link Controller#provider} repository.
     *
     * @param request the initial request that contains the data
     *                for processing
     * @return the result response of the found command.
     */
    public String executeTask(final String request) {
        String commandName;
        Command executionCommand;
        String response;

        commandName = request.substring(0, request.indexOf(PARAM_DELIMITER));
        try {
            int code = Integer.parseInt(commandName);
            executionCommand = provider.getCommand(code);

            String params = request.substring(request
                    .indexOf(PARAM_DELIMITER));
            response = executionCommand.execute(params);
        } catch (NumberFormatException e) {
            response = MessageManager
                    .getProperty("message.incorrect.args.format");
        }
        return response;
    }
}

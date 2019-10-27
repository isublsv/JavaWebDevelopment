package by.gartsmanovich.webparsing.controller.command;

import by.gartsmanovich.webparsing.controller.command.impl.DOMBuilderCommand;
import by.gartsmanovich.webparsing.controller.command.impl.SAXBuilderCommand;
import by.gartsmanovich.webparsing.controller.command.impl.StAXBuilderCommand;
import by.gartsmanovich.webparsing.controller.command.impl.WrongRequestCommand;

import java.util.EnumMap;
import java.util.Map;

/**
 * Class used to store and determine the different kind of commands.
 *
 * @author Dmitry Gartsmanovich
 */
public class CommandProvider {

    /**
     * The map contains the list of supported commands and its values.
     */
    private final Map<CommandName, Command> repository = new EnumMap<>(
            CommandName.class);

    /**
     * Creates and fills the repository of supported commands.
     */
    public CommandProvider() {
        repository.put(CommandName.DOM, new DOMBuilderCommand());
        repository.put(CommandName.SAX, new SAXBuilderCommand());
        repository.put(CommandName.STAX, new StAXBuilderCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
    }

    /**
     * Returns the command instance for which the specified code is mapped
     * in the repository.
     *
     * @param code the code whose associated value is to be returned.
     * @return the command instance.
     */
    public Command getCommand(final String code) {
        Command command;

        try {
            command = repository.get(
                    CommandName.values()[Integer.parseInt(code) - 1]);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}

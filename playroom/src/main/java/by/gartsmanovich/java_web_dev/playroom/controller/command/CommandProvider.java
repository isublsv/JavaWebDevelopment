package by.gartsmanovich.java_web_dev.playroom.controller.command;

import by.gartsmanovich.java_web_dev.playroom.controller.command.impl.AddToy;
import by.gartsmanovich.java_web_dev.playroom.controller.command.impl
        .WrongRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    /**
     * The map contains the list of supported commands and its values.
     */
    private final Map<CommandName, Command> repository = new HashMap<>();

    /**
     * Creates and fills the repository of supported commands.
     */
    public CommandProvider() {
        repository.put(CommandName.ADD_TOY, new AddToy());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    /**
     * Returns the command instance for which the specified name is mapped
     * in the repository.
     *
     * @param name the key whose associated value is to be returned.
     * @return the command instance.
     */
    public Command getCommand(final String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}

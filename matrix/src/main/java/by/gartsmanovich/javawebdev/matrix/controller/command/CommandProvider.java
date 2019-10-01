package by.gartsmanovich.javawebdev.matrix.controller.command;

import by.gartsmanovich.javawebdev.matrix.controller.command.impl.CreateMatrix;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.Exit;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.Option1;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.Option2;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.Option3;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.Option4;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.WrongRequest;

import java.util.EnumMap;
import java.util.Map;

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
        repository.put(CommandName.CREATE_MATRIX, new CreateMatrix());
        repository.put(CommandName.OPTION_1, new Option1());
        repository.put(CommandName.OPTION_2, new Option2());
        repository.put(CommandName.OPTION_3, new Option3());
        repository.put(CommandName.OPTION_4, new Option4());
        repository.put(CommandName.EXIT, new Exit());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    /**
     * Returns the command instance for which the specified name is mapped
     * in the repository.
     *
     * @param name the name whose associated value is to be returned.
     * @return the command instance.
     */
    public Command getCommand(final String name) {
        Command command;

        try {
            command = repository.get(CommandName.valueOf(name));
        } catch (IllegalArgumentException | NullPointerException e) {

            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}

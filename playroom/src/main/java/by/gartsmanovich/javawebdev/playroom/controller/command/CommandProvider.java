package by.gartsmanovich.javawebdev.playroom.controller.command;

import by.gartsmanovich.javawebdev.playroom.controller.command.impl.AddToy;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .CreatePlayRoom;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl.Exit;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .FindToyByFirstLetter;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .FindToyByID;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .FindToyByRangeID;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .FindToyByTitle;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .PrintAll;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .RemoveToy;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .SortByAge;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .SortByColorAndPrice;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
        .UpdateToy;
import by.gartsmanovich.javawebdev.playroom.controller.command.impl
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
        repository.put(CommandName.CREATE_PLAYROOM, new CreatePlayRoom());
        repository.put(CommandName.ADD_TOY, new AddToy());
        repository.put(CommandName.UPDATE_TOY, new UpdateToy());
        repository.put(CommandName.REMOVE_TOY, new RemoveToy());
        repository.put(CommandName.FIND_TOY_BY_ID, new FindToyByID());
        repository.put(CommandName.FIND_TOY_BY_TITLE, new FindToyByTitle());
        repository.put(CommandName.FIND_TOYS_BY_FIRST_TITLE_LETTER,
                new FindToyByFirstLetter());
        repository.put(CommandName.FIND_TOYS_BY_RANGE_ID,
                new FindToyByRangeID());
        repository.put(CommandName.SORT_BY_AGE, new SortByAge());
        repository.put(CommandName.SORT_BY_COLOR_AND_PRICE,
                new SortByColorAndPrice());
        repository.put(CommandName.PRINT_ALL, new PrintAll());
        repository.put(CommandName.EXIT, new Exit());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    /**
     * Returns the command instance for which the specified name is mapped
     * in the repository.
     *
     * @param code the key whose associated value is to be returned.
     * @return the command instance.
     */
    public Command getCommand(final int code) {
        Command command;

        try {
            command = repository.get(CommandName.values()[code]);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}

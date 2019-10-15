package by.gartsmanovich.composite.controller.command;

import by.gartsmanovich.composite.controller.command.impl.WrongRequestCommand;
import by.gartsmanovich.composite.controller.command.impl.CreateCompositeCommand;
import by.gartsmanovich.composite.controller.command.impl.SortLexemesCommand;
import by.gartsmanovich.composite.controller.command.impl.ExitCommand;
import by.gartsmanovich.composite.controller.command.impl.SortWordsCommand;
import by.gartsmanovich.composite.controller.command.impl.SaveCompositeCommand;
import by.gartsmanovich.composite.controller.command.impl.SortParagraphsCommand;

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
        repository.put(CommandName.CREATE_COMPOSITE,
                       new CreateCompositeCommand());
        repository.put(CommandName.SORT_PARAGRAPHS,
                       new SortParagraphsCommand());
        repository.put(CommandName.SORT_WORDS, new SortWordsCommand());
        repository.put(CommandName.SORT_LEXEMES,
                       new SortLexemesCommand());
        repository.put(CommandName.SAVE_COMPOSITE, new SaveCompositeCommand());
        repository.put(CommandName.EXIT, new ExitCommand());
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

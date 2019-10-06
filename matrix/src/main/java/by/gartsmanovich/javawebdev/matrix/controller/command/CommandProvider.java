package by.gartsmanovich.javawebdev.matrix.controller.command;

import by.gartsmanovich.javawebdev.matrix.controller.command.impl.
        CreateMatrixCommand;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.
        ExecutorServiceCommand;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.ExitCommand;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.LockCommand;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl
        .SaveLastCommand;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.
        SemaphoreCommand;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.
        ThreadDistributionCommand;
import by.gartsmanovich.javawebdev.matrix.controller.command.impl.
        WrongRequestCommand;

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
        repository.put(CommandName.CREATE_MATRIX, new CreateMatrixCommand());
        repository.put(CommandName.THREAD_DISTRIBUTION,
                       new ThreadDistributionCommand());
        repository.put(CommandName.LOCK, new LockCommand());
        repository.put(CommandName
                               .EXECUTOR_SERVICE, new ExecutorServiceCommand());
        repository.put(CommandName.SEMAPHORE, new SemaphoreCommand());
        repository.put(CommandName.SAVE_LAST, new SaveLastCommand());
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
            command = repository
                    .get(CommandName.values()[Integer.parseInt(code) - 1]);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}

package by.gartsmanovich.javawebdev.matrix.controller.command;

/**
 * Enum-class represents the list of commands that exists in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public enum CommandName {

    /**
     * The singleton instance for the matrix creation command.
     * This has the numeric value of {@code 0}.
     */
    CREATE_MATRIX,

    /**
     * The singleton instance for the thread distribution command.
     * This has the numeric value of {@code 1}.
     */
    THREAD_DISTRIBUTION,

    /**
     * The singleton instance for the lock command.
     * This has the numeric value of {@code 2}.
     */
    LOCK,

    /**
     * The singleton instance for the executor service command.
     * This has the numeric value of {@code 3}.
     */
    EXECUTOR_SERVICE,

    /**
     * The singleton instance for the semaphore command.
     * This has the numeric value of {@code 4}.
     */
    SEMAPHORE,

    /**
     * The singleton instance for the save last result command.
     * This has the numeric value of {@code 5}.
     */
    SAVE_LAST,

    /**
     * The singleton instance for the exit command.
     * This has the numeric value of {@code 6}.
     */
    EXIT,

    /**
     The singleton instance for the wrong request command.
     * This has the numeric value of {@code 7}.
     */
    WRONG_REQUEST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

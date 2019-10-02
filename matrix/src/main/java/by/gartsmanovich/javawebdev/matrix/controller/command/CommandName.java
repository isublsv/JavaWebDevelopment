package by.gartsmanovich.javawebdev.matrix.controller.command;

public enum CommandName {

    /**
     * The singleton instance for the matrix creation command.
     * This has the numeric value of {@code 0}.
     */
    CREATE_MATRIX,

    /**
     * The singleton instance for the first option command.
     * This has the numeric value of {@code 1}.
     */
    SIMPLE,

    /**
     * The singleton instance for the second option command.
     * This has the numeric value of {@code 2}.
     */
    OPTION_2,

    /**
     * The singleton instance for the third option command.
     * This has the numeric value of {@code 3}.
     */
    EXECUTOR_SERVICE,

    /**
     * The singleton instance for the forth option command.
     * This has the numeric value of {@code 4}.
     */
    SEMAPHORE,

    /**
     * The singleton instance for the exit command.
     * This has the numeric value of {@code 5}.
     */
    EXIT,

    /**
     The singleton instance for the wrong request command.
     * This has the numeric value of {@code 6}.
     */
    WRONG_REQUEST
}

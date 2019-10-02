package by.gartsmanovich.javawebdev.matrix.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecutorMatrixThread implements Runnable {

    /**
     * The logger for ExecutorMatrixThread class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ExecutorMatrixThread.class);

    /**
     * Contains the array instance of integers.
     */
    private final int[][] array;

    /**
     * Contains a new diagonal's value of the 2-d array.
     */
    private int value;

    /**
     * Constructs the new thread with specific parameters.
     *
     * @param arrayValue the array instance of integers.
     * @param newValue   a new diagonal's value of the 2-d array.
     */
    public ExecutorMatrixThread(final int[][] arrayValue, final int newValue) {
        array = arrayValue;
        value = newValue;
    }

    /**
     * Finds the "empty" diagonal index in the provided array and sets the
     * provided value.
     */
    @Override
    public void run() {
        synchronized (array) {
            for (int i = 0; i <= array.length; i++) {
                if (array[i][i] == 0) {
                    array[i][i] = value;
                    String message =
                            Thread.currentThread().getName() + " has insert "
                            + value + " at " + i + " position.";
                    LOGGER.debug(message);
                    break;
                }
            }
        }
    }
}

package by.gartsmanovich.javawebdev.matrix.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleMatrixThread implements Runnable {

    /**
     * The logger for ExecutorMatrixThread class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            SimpleMatrixThread.class);

    /**
     * Contains the array instance of integers.
     */
    private final int[][] array;

    /**
     * Contains a new values of diagonals.
     */
    private int[] values;

    /**
     * Contain the start index in the array that current thread will be change.
     */
    private int start;

    /**
     * Contain the end index in the array that current thread will be change.
     */
    private int end;

    /**
     * Constructs the new thread with specific parameters.
     *
     * @param arrayValue the array instance of integers.
     * @param diagValues the array instance with diagonal values.
     * @param startValue the start index int the array.
     * @param endValue the end index in the array.
     */
    public SimpleMatrixThread(final int[][] arrayValue, final int[] diagValues,
            final int startValue, final int endValue) {
        array = arrayValue;
        values = diagValues;
        start = startValue;
        end = endValue;
    }

    /**
     * Finds the "empty" diagonal index in the provided array and sets the
     * provided value.
     */
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = start; i <= end; i++) {
            if (array[i][i] == 0) {
                array[i][i] = values[i];
                String message =
                        threadName + " has insert "
                        + values[i] + " at " + i + " position.";
                LOGGER.debug(message);
            }
        }
    }
}

package by.gartsmanovich.javawebdev.matrix.bean.thread;

import by.gartsmanovich.javawebdev.matrix.bean.BasicThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class used to write provided values to the range of indexes of 2d array
 * in the separate thread.
 *
 * @author Dmitry Gartsmanovich
 */
public class SimpleMatrixThread extends BasicThread implements Runnable {

    /**
     * The logger for SimpleMatrixThread class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            SimpleMatrixThread.class);

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
     * @param idValue the ID of the thread.
     * @param nameValue the name of the thread.
     * @param arrayValue the array instance of integers.
     * @param diagValues the array instance with diagonal values.
     * @param startValue the start index int the array.
     * @param endValue the end index in the array.
     */
    public SimpleMatrixThread(final int idValue, final String nameValue,
            final int[][] arrayValue, final int[] diagValues,
            final int startValue, final int endValue) {
        super(idValue, nameValue, arrayValue);
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
        for (int i = start; i <= end; i++) {
            if (getArray()[i][i] == 0) {
                getArray()[i][i] = values[getId()];
                String message = getName() + " has insert " + values[getId()]
                                 + " at " + i + " position.";
                LOGGER.debug(message);
            }
        }
    }
}

package by.gartsmanovich.javawebdev.matrix.bean.thread;

import by.gartsmanovich.javawebdev.matrix.bean.BasicThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Class used to write provided values to the 2d array using
 * {@link java.util.concurrent.ExecutorService} and
 * {@link java.util.concurrent.atomic.AtomicInteger} technologies in the
 * separate threads.
 *
 * @author Dmitry Gartsmanovich
 */
public class ExecutorMatrixThread extends BasicThread implements Runnable {

    /**
     * The logger for ExecutorMatrixThread class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ExecutorMatrixThread.class);

    /**
     * Contains the current start index value of the range for current thread.
     */
    private static AtomicInteger startRange = new AtomicInteger(0);

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
     */
    public ExecutorMatrixThread(final int idValue, final String nameValue,
            final int[][] arrayValue, final int[] diagValues) {
        super(idValue, nameValue, arrayValue, diagValues);
        perform();
    }

    /**
     * Finds the "empty" diagonal index in the provided array and sets the
     * provided value.
     */
    @Override
    public void run() {
        IntStream.rangeClosed(start, end)
                 .filter(i -> getArray()[i][i] == 0)
                 .forEach(i -> {
                     getArray()[i][i] = getValues()[getId()];
                     String message =
                             String.format("%s has insert %d at %d position",
                             getName(), getValues()[getId()], i);
                     LOGGER.debug(message);
                 });
    }

    /**
     * Calculates the start and the end indexes of active thread depends on
     * {@link ExecutorMatrixThread#startRange} value.
     */
    private void perform() {
        int count = getArray().length / getValues().length;
        int additional = getArray().length % getValues().length;

        if (getId() == 0) {
            start = 0;
            end = count + additional - 1;
        } else {
            start = startRange.get();
            end = start + count - 1;
        }
        startRange.set(end + 1);
    }
}

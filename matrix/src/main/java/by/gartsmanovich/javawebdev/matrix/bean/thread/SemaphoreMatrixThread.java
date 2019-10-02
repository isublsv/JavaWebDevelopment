package by.gartsmanovich.javawebdev.matrix.bean.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class SemaphoreMatrixThread implements Runnable {

    /**
     * The logger for SemaphoreMatrixThread class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            SemaphoreMatrixThread.class);

    /**
     * The Semaphore instance used by thread to acquire a permit to provided
     * array.
     */
    private Semaphore semaphore;
    /**
     * Contains the array instance of integers.
     */
    private int[][] array;

    /**
     * Contains a new diagonal's value of the 2-d array.
     */
    private int value;

    /**
     * Constructs the new thread with specific parameters.
     *
     * @param semaphoreValue the provided semaphore instance.
     * @param arrayValue the array instance of integers.
     * @param newValue   a new diagonal's value of the 2-d array.
     */
    public SemaphoreMatrixThread(final Semaphore semaphoreValue,
            final int[][] arrayValue, final int newValue) {
        semaphore = semaphoreValue;
        array = arrayValue;
        value = newValue;
    }

    /**
     * Finds the "empty" diagonal index in the provided array and sets the
     * provided value.
     */
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            semaphore.acquire();
                for (int i = 0; i < array.length; i++) {
                    if (array[i][i] == 0) {
                        array[i][i] = value;
                        String message =
                                threadName + " has insert "
                                + value + " at " + i + " position. "
                                + " Available permits: " + semaphore
                                        .availablePermits();
                        LOGGER.debug(message);
                        break;
                    }
                }
            semaphore.release();
        } catch (InterruptedException e) {
            String errorMessage = threadName + " was interrupted";
            LOGGER.error(errorMessage);
            Thread.currentThread().interrupt();
        }
    }
}

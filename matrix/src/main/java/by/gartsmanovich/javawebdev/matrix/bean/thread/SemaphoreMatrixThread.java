package by.gartsmanovich.javawebdev.matrix.bean.thread;

import by.gartsmanovich.javawebdev.matrix.bean.BasicThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * Class used to write provided values to the 2d array using
 * {@link java.util.concurrent.Semaphore} technology in the separate
 * thread.
 *
 * @author Dmitry Gartsmanovich
 */
public class SemaphoreMatrixThread extends BasicThread implements Runnable {

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
     * Constructs the new thread with specific parameters.
     *
     * @param idValue the ID of the thread.
     * @param nameValue the name of the thread.
     * @param arrayValue the array instance of integers.
     * @param diagValues the array instance with diagonal values.
     * @param semaphoreValue the provided semaphore instance.
     */
    public SemaphoreMatrixThread(final int idValue, final String nameValue,
            final int[][] arrayValue, final int[] diagValues,
            final Semaphore semaphoreValue) {
        super(idValue, nameValue, arrayValue, diagValues);
        semaphore = semaphoreValue;
    }

    /**
     * Finds the "empty" diagonal index in the provided array and sets the
     * provided value.
     */
    @Override
    public void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < getArray().length; i++) {
                if (getArray()[i][i] == 0) {
                    getArray()[i][i] = getValues()[getId()];
                    String message =
                            getName() + " has insert " + getValues()[getId()]
                            + " at " + i + " position. "
                                + " Available permits: " + semaphore
                                        .availablePermits();
                        LOGGER.debug(message);
                    break;
                    }
                }
            semaphore.release();
        } catch (InterruptedException e) {
            String errorMessage = getName() + " was interrupted";
            LOGGER.error(errorMessage);
            Thread.currentThread().interrupt();
        }
    }
}

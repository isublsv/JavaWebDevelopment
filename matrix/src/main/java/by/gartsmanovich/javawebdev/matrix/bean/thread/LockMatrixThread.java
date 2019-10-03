package by.gartsmanovich.javawebdev.matrix.bean.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class LockMatrixThread implements Runnable {

    /**
     * The logger for LockMatrixThread class.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(LockMatrixThread.class);

    /**
     * The timeout for the set a new value operation.
     */
    private static final int TIMEOUT = 100;

    /**
     * Contains the array instance of integers.
     */
    private int[][] array;

    /**
     * Contains a new values of diagonals.
     */
    private int[] values;

    /**
     * Contains the lock instance.
     */
    private Lock lock;

    /**
     * Constructs the new thread with specific parameters.
     *
     * @param lockValue  the lock instance for blocking threads.
     * @param arrayValue the array instance of integers.
     * @param diagValues a new diagonal's value of the 2-d array.
     */
    public LockMatrixThread(final Lock lockValue, final int[][] arrayValue,
                            final int[] diagValues) {
        lock = lockValue;
        array = arrayValue;
        values = diagValues;
    }

    /**
     * Finds the "empty" diagonal index in the provided array and sets the
     * provided value.
     */
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < array.length; i++) {
            try {
                lock.lock();
                try {
                    if (array[i][i] == 0) {
                        array[i][i] = values[i];
                        String message =
                                threadName + " has insert " + values[i] + " "
                                        + "at " + i + " position.";
                        LOGGER.debug(message);
                    }
                } finally {
                    lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                String errorMessage = threadName + " was interrupted";
                LOGGER.error(errorMessage);
                Thread.currentThread().interrupt();
            }
        }
    }
}

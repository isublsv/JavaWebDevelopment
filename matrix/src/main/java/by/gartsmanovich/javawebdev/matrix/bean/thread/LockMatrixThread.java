package by.gartsmanovich.javawebdev.matrix.bean.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class LockMatrixThread implements Runnable {

    /**
     * The logger for LockMatrixThread class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            LockMatrixThread.class);

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
     * Each value of array signals that appropriate value in the values array
     * was taken to the 2d array.
     */
    private boolean[] isTaken;
    /**
     * Contains the lock instance.
     */
    private Lock lock;

    /**
     * Constructs the new thread with specific parameters.
     *
     * @param lockValue the lock instance for blocking threads.
     * @param arrayValue the array instance of integers.
     * @param diagValues a new diagonal's value of the 2-d array.
     */
    public LockMatrixThread(final Lock lockValue, final int[][] arrayValue,
            final int[] diagValues) {
        lock = lockValue;
        array = arrayValue;
        values = diagValues;
        isTaken = new boolean[diagValues.length];
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
                if (lock.tryLock(TIMEOUT, TimeUnit.MILLISECONDS)) {
                    if (!isTaken[i] && array[i][i] == 0) {
                        array[i][i] = values[i];
                    }
                    String message =
                            threadName + " has insert " + values[i] + " at "
                            + i + " position.";
                    LOGGER.debug(message);
                    isTaken[i] = true;
                }
            } catch (InterruptedException e) {
                String errorMessage = threadName + " was interrupted";
                LOGGER.error(errorMessage);
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}

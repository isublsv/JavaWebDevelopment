package by.gartsmanovich.javawebdev.matrix.bean.thread;

import by.gartsmanovich.javawebdev.matrix.bean.BasicThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Class used to write provided values to the 2d array using
 * {@link java.util.concurrent.locks.ReentrantLock} technology in the separate
 * thread.
 */
public class LockMatrixThread extends BasicThread implements Runnable {

    /**
     * The logger for LockMatrixThread class.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(LockMatrixThread.class);

    /**
     * The timeout for the set a new value operation.
     */
    private static final int TIMEOUT = 30;

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
     * @param idValue the ID of the thread.
     * @param nameValue the name of the thread.
     * @param arrayValue the array instance of integers.
     * @param lockValue  the lock instance for blocking threads.
     * @param diagValues a new diagonal's value of the 2-d array.
     */
    public LockMatrixThread(final int idValue, final String nameValue,
            final int[][] arrayValue, final Lock lockValue,
            final int[] diagValues) {
        super(idValue, nameValue, arrayValue);
        lock = lockValue;
        values = diagValues;
    }

    /**
     * Finds the "empty" diagonal index in the provided array and sets the
     * provided value.
     */
    @Override
    public void run() {
        for (int i = getId(); i < getArray().length; i++) {
            //find first empty diagonal value
            if (getArray()[i][i] == 0) {
                try {
                    //trying to acquire the lock
                    boolean flag = lock.tryLock(TIMEOUT, TimeUnit.MILLISECONDS);
                    if (flag) {
                        try {
                            //check if the value is still empty
                            if (getArray()[i][i] == 0) {
                                //set new value
                                getArray()[i][i] = values[getId()];
                                String message = getName() + " has insert "
                                                 + values[getId()] + " at " + i
                                                 + " position.";
                                LOGGER.debug(message);
                            }
                        } finally {
                            lock.unlock();
                        }
                        TimeUnit.MILLISECONDS.sleep(TIMEOUT);
                    }
                } catch (InterruptedException e) {
                    String errorMessage = getName() + " was interrupted";
                    LOGGER.error(errorMessage);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

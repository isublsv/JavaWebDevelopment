package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.thread.LockMatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification
        .Specification;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Specification represents the solution of the task that using
 * {@link java.util.concurrent.locks.ReentrantLock} realisation to distribute
 * provided vales between active threads and put them into the provided 2d
 * array.
 *
 * @author Dmitry Gartsmanovich
 */
public class FillByLockSpecification implements Specification {

    /**
     * Applies specified criteria to the provided array.
     *
     * @param threadNumber the number of active threads.
     * @param values       the array of provided values for the main diagonal.
     * @param array        the 2d array with provided integers.
     * @return the 2d array that correspond to specified criteria.
     */
    @Override
    public int[][] specified(final int threadNumber, final int[] values,
            final int[][] array) {

        int[][] copy = getCopy(array);

        Lock lock = new ReentrantLock(true);

        Thread[] threads = new Thread[threadNumber];

        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new Thread(
                    new LockMatrixThread(i, "LockThread", copy, lock, values));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return copy;
    }
}

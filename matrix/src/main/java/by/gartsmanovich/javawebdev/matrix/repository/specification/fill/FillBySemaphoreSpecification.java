package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.thread.SemaphoreMatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification
        .Specification;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Specification represents the solution of the task that using
 * {@link java.util.concurrent.Semaphore} realisation to distribute
 * provided vales between active threads and put them into the provided 2d
 * array.
 *
 * @author Dmitry Gartsmanovich
 */
public class FillBySemaphoreSpecification implements Specification {

    /**
     * The timeout for the set a new value operation.
     */
    private static final int TIMEOUT = 500;

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

        int permitNumber = array.length / threadNumber + 1;
        Semaphore semaphore = new Semaphore(permitNumber, true);

        int[][] copy = getCopy(array);

        int currentID = 0;
        for (int i = 0; i < copy.length; i++) {
            currentID++;
            if (currentID == threadNumber) {
                currentID = 0;
            }
            new Thread(new SemaphoreMatrixThread(currentID, "SemaphoreThread",
                                                 copy, values,
                                                 semaphore)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return copy;
    }
}

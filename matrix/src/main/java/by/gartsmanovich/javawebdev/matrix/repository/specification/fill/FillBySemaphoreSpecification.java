package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.SemaphoreMatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification.Specification;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FillBySemaphoreSpecification implements Specification {

    /**
     * The timeout for the set a new value operation.
     */
    private static final int TIMEOUT = 100;

    /**
     * Applies specified criteria to the provided array.
     *
     * @param threadNumber the number of active threads.
     * @param values       the array of provided values for the main diagonal.
     * @param array        with provided integers.
     * @return the array that correspond to specified criteria,
     * false - otherwise.
     */
    @Override
    public int[][] specified(final int threadNumber, final int[] values,
            final int[][] array) {

        Semaphore semaphore = new Semaphore(threadNumber, true);

        int[][] copy = getCopy(array);

        for (int value : values) {
            new Thread(
                    new SemaphoreMatrixThread(semaphore, copy, value)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return copy;
    }
}

package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.thread.ExecutorMatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification
        .Specification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Specification represents the solution of the task that using
 * {@link java.util.concurrent.ExecutorService} realisation to distribute
 * provided vales between active threads and put them into the provided 2d
 * array.
 *
 * @author Dmitry Gartsmanovich
 */
public class FillByExecutorSpecification implements Specification {

    /**
     * The timeout for the set a new value operation.
     */
    private static final int TIMEOUT = 100;

    /**
     * Applies specified criteria to the provided array.
     *
     * @param threadNumber the number of active threads.
     * @param values      the array of provided values for the main diagonal.
     * @param array       with provided integers.
     * @return the array that correspond to specified criteria,
     * false - otherwise.
     */
    @Override
    public int[][] specified(final int threadNumber,
            final int[] values, final int[][] array) {

        int poolLimit = array.length / threadNumber + 1;

        ExecutorService executorService = Executors
                .newFixedThreadPool(poolLimit);

        int[][] copy = getCopy(array);

        for (int i = 0; i < threadNumber; i++) {
            executorService.execute(
                    new ExecutorMatrixThread(i, "ExecutorThread",
                                             copy, values));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            try {
                TimeUnit.MILLISECONDS.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return copy;
    }
}

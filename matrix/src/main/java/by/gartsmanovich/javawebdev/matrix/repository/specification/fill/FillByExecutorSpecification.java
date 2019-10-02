package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.ExecutorMatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification.Specification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

        ExecutorService executorService = Executors
                .newFixedThreadPool(threadNumber);

        int[][] copy = getCopy(array);

        for (int value : values) {
            executorService.execute(new ExecutorMatrixThread(copy, value));
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

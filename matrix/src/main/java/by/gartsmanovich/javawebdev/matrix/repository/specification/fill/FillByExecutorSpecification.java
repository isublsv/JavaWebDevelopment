package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.MatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification
        .Specification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FillByExecutorSpecification implements Specification {

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
            executorService.submit(new MatrixThread(copy, value));
        }

        executorService.shutdown();

        return copy;
    }
}

package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.ExecutorMatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification.Specification;

public class FillBySynchronisedSpecification implements Specification {

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

        int[][] copy = getCopy(array);

        for (int value : values) {
            Thread thread = new Thread(new ExecutorMatrixThread(copy, value));
            thread.start();
        }
        return copy;
    }
}

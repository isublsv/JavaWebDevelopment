package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.thread.SimpleMatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification
        .Specification;

public class SeparateFillSpecification implements Specification {

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

        int count = array.length / threadNumber;
        int additional = array.length % threadNumber;

        Thread[] threads = new Thread[threadNumber];

        int start = 0;
        for (int i = 0; i < threads.length; i++) {
            int currentCount = (i == 0) ? count + additional : count;
            threads[i] = new Thread(new SimpleMatrixThread(copy, values, start,
                                                        start + currentCount
                                                        - 1));
            start = start + currentCount;
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

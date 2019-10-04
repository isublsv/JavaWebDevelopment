package by.gartsmanovich.javawebdev.matrix.repository.specification;

import java.util.Arrays;

/**
 * Common interface for specifications.
 */
public interface Specification {

    /**
     * Applies specified criteria to the provided array.
     *
     * @param threadNumber the number of active threads.
     * @param values       the array of provided values for the main diagonal.
     * @param array        the 2d array with provided integers.
     * @return the array that correspond to specified criteria,
     * false - otherwise.
     */
    int[][] specified(int threadNumber, int[] values, int[][] array);

    /**
     * Makes a new copy of provided array.
     *
     * @param array the provided value of array.
     * @return the new copy of provided array.
     */
    default int[][] getCopy(int[][] array) {
        return Arrays.stream(array)
                     .map(r -> Arrays.copyOf(r, r.length))
                     .toArray(int[][]::new);
    }
}

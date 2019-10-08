package by.gartsmanovich.javawebdev.composite.service.validator;

import java.util.List;

/**
 * Utility class used to validate provided data.
 *
 * @author Dmitry Gartsmanovich
 */
public class Validator {

    /**
     * The minimum valid number of active threads.
     */
    private static final int MIN_THREADS = 4;

    /**
     * The maximum valid number of active threads.
     */
    private static final int MAX_THREADS = 6;

    /**
     * The minimum valid value of dimension of the array.
     */
    private static final int MIN_DIM = 8;

    /**
     * The maximum valid value of dimension of the array.
     */
    private static final int MAX_DIM = 12;

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final String value) {
        return value != null && !value.isEmpty();
    }

    /**
     * Checks if the value is a number.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isNumber(final String value) {
        if (isValidValue(value)) {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the list of string is a square matrix.
     *
     * @param list      the list to valid.
     * @param delimiter the delimiter that splits the string if matches.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isSquareMatrix(final List<String> list,
            final String delimiter) {

        int verticalSize = list.size();
        int horizontalSize = list.get(0).split(delimiter).length;

        for (String s : list) {
            String[] row = s.split(delimiter);
            if (row.length != verticalSize) {
                return false;
            }
        }
        return verticalSize == horizontalSize;
    }

    /**
     * Checks if provided value of the thread number is in the valid range.
     *
     * @param threadNum the provided value of the thread number.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidThreadNumber(final int threadNum) {
        return threadNum >= MIN_THREADS && threadNum <= MAX_THREADS;
    }

    /**
     * Checks if provided number of diagonal values is equals the number of
     * active threads.
     *
     * @param diagValue the array of diagonal values.
     * @param threadNumber the number of active threads.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidDiagValues(final int[] diagValue,
            final int threadNumber) {
        return threadNumber != diagValue.length;
    }

    /**
     * Checks if provided dimension of the array is valid.
     *
     * @param n the dimension of the array.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidDimension(final int n) {
        return n >= MIN_DIM && n <= MAX_DIM;
    }

    /**
     * Checks if provided values equals to 0.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidDiagonalValue(final String value) {
        if (isNumber(value)) {
            return Integer.parseInt(value) != 0;
        } else {
            return false;
        }
    }
}

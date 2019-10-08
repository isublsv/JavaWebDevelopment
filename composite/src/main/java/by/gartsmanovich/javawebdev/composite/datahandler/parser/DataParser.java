package by.gartsmanovich.javawebdev.composite.datahandler.parser;

import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .DataHandlerException;
import by.gartsmanovich.javawebdev.composite.service.validator.Validator;

import java.util.List;

/**
 * The class used to parse provided string data to the valid numeric data.
 *
 * @author Dmitry Gartsmanovich
 */
public class DataParser {

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * Constructs an instance of data parser.
     */
    public DataParser() {
        validator = new Validator();
    }

    /**
     * Returns the matrix instance with valid ints from the data string.
     *
     * @param data      the list of strings.
     * @param delimiter the delimiter that splits the string if matches.
     * @return the list of valid entities.
     * @throws DataHandlerException if error happens during execution.
     */
    public String parseData(final List<String> data,
            final String delimiter) throws DataHandlerException {
        return null;
    }

    /**
     * Creates the 2d array of integers from list of strings using delimiter.
     *
     * @param subList   the list to processing.
     * @param delimiter the delimiter to split the provided strings from
     *                  the list.
     * @return the valid 2d array of integers.
     * @throws DataHandlerException if error happens during execution.
     */
    private int[][] getMatrixInts(final List<String> subList,
            final String delimiter) throws DataHandlerException {

        if (validator.isSquareMatrix(subList, delimiter)) {

            int n = subList.size();
            if (!validator.isValidDimension(n)) {
                String message = String.format(
                        "The dimension of the matrix in the file is invalid:"
                        + " %d", n);
                throw new DataHandlerException(message);
            }

            return getArr(subList, delimiter, n);
        } else {
            String message = "The data in the file is not square matrix!";
            throw new DataHandlerException(message);
        }
    }

    /**
     * Check and fill 2d array with valid numbers.
     *
     * @param subList   the list to processing.
     * @param delimiter the delimiter to split the provided strings from
     *                  the list.
     * @param n         the valid dimension of array.
     * @return the valid 2d array of integers.
     * @throws DataHandlerException if error happens during execution.
     */
    private int[][] getArr(final List<String> subList, final String delimiter,
            final int n) throws DataHandlerException {

        int[][] arr = new int[n][n];
        for (int i = 0; i < subList.size(); i++) {

            String[] row = subList.get(i).split(delimiter);
            if (validator.isValidDiagonalValue(row[i])) {
                String message = String.format(
                        "The diagonal value for the matrix in the file is "
                        + "not equal to 0: %s", row[i]);
                throw new DataHandlerException(message);
            }

            for (int j = 0; j < row.length; j++) {
                if (validator.isNumber(row[j])) {
                    arr[i][j] = Integer.parseInt(row[j]);
                } else {
                    String message = String.format(
                            "The values for the matrix in the file are "
                            + "invalid: %s", row[j]);
                    throw new DataHandlerException(message);
                }
            }
        }
        return arr;
    }

    /**
     * Returns the valid array of integers used to write to the diagonal
     * positions of the 2d array.
     *
     * @param data      the data list to processing.
     * @param delimiter the delimiter to split the provided string from
     *                  the list.
     * @return the valid array of the diagonal values.
     * @throws DataHandlerException if error happens during execution.
     */
    private int[] getDiagInts(final List<String> data,
            final String delimiter) throws DataHandlerException {

        String[] diagArrStrings = data.get(1).split(delimiter);

        int[] diagArrInts = new int[diagArrStrings.length];

        for (int i = 0; i < diagArrStrings.length; i++) {
            String s = diagArrStrings[i];
            if (validator.isNumber(s)) {
                diagArrInts[i] = Integer.parseInt(s);
            } else {
                String message = String.format(
                        "The diagonal values in the file are invalid: %s", s);
                throw new DataHandlerException(message);
            }
        }
        return diagArrInts;
    }

    /**
     * Returns the valid number of thread used in the application.
     *
     * @param data the data list to processing.
     * @return the valid number of the active thread.
     * @throws DataHandlerException if error happens during execution.
     */
    private int getThreadNumber(final List<String> data) throws
            DataHandlerException {

        String threadNumStr = data.get(0).trim();

        if (validator.isNumber(threadNumStr)) {
            return Integer.parseInt(threadNumStr);
        } else {
            String message = String.format(
                    "The thread number in the file contains invalid data: %s",
                    threadNumStr);
            throw new DataHandlerException(message);
        }
    }
}

package by.gartsmanovich.javawebdev.matrix.datahandler.parser;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;
import by.gartsmanovich.javawebdev.matrix.service.validator.Validator;

import java.util.Arrays;
import java.util.List;

/**
 * The class used to parse provided string data to the valid numeric data.
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
    public Matrix parseData(final List<String> data,
            final String delimiter) throws DataHandlerException {

        int threadNumber = getThreadNumber(data);

        int[] diagValue = getDiagInts(data, delimiter);

        int[][] ints = getMatrixInts(data.subList(2, data.size()), delimiter);

        if (!validator.isValidThreadNumber(threadNumber)
            || validator.isValidDiagValues(diagValue, threadNumber)) {
            String message = "The thread number: " + threadNumber
                             + " or the number of thread values: "
                             + Arrays.toString(diagValue) + " are not valid!";
            throw new DataHandlerException(message);
        } else {
            return Matrix.getInstance(threadNumber, diagValue, ints);
        }
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
            int[][] arr = new int[n][n];

            for (int i = 0; i < subList.size(); i++) {
                String[] row = subList.get(i).split(delimiter);
                for (int j = 0; j < row.length; j++) {
                    if (validator.isNumber(row[j])) {
                        arr[i][j] = Integer.parseInt(row[j]);
                    } else {
                        String message = "The values for the matrix in the file"
                                         + " are invalid: " + row[j];
                        throw new DataHandlerException(message);
                    }
                }
            }

            return arr;
        } else {
            String message = "The data in the file is not square matrix!";
            throw new DataHandlerException(message);
        }
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
                String message = "The diagonal values in the file"
                                 + " are invalid: " + s;
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
            String message =
                    "The thread number in the file contains invalid data: "
                    + threadNumStr;
            throw new DataHandlerException(message);
        }
    }
}

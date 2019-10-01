package by.gartsmanovich.javawebdev.matrix.datahandler.parser;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;
import by.gartsmanovich.javawebdev.matrix.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DataParser {

    /**
     * The logger for Data Reader implementation class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            DataParser.class);

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
     * Returns the matrix instance wit valid ints from the data string.
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

        int[][] ints = getMatrixInts(data.subList(2, data.size() - 1),
                                     delimiter);

        return Matrix.getInstance(threadNumber, diagValue, ints);
    }

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
                        String message = "The int values for matrix contains"
                                         + "wrong data" + row[j];
                        LOGGER.error(message);
                        throw new DataHandlerException(message);
                    }
                }
            }

            return arr;
        } else {
            String message = "The data in the file is not square matrix";
            LOGGER.error(message);
            throw new DataHandlerException(message);
        }
    }

    private int[] getDiagInts(final List<String> data,
            final String delimiter) throws DataHandlerException {

        String[] diagArrStrings = data.get(1).split(delimiter);

        int[] diagArrInts = new int[diagArrStrings.length];

        for (int i = 0; i < diagArrStrings.length; i++) {
            String s = diagArrStrings[i];
            if (validator.isNumber(s)) {
                diagArrInts[i] = Integer.parseInt(s);
            } else {
                String message = "The diagonal values in the file contains"
                                 + " wrong data" + s;
                LOGGER.error(message);
                throw new DataHandlerException(message);
            }
        }
        return diagArrInts;
    }

    private int getThreadNumber(final List<String> data) throws
            DataHandlerException {

        String threadNumStr = data.get(0).trim();

        if (validator.isNumber(threadNumStr)) {
            return Integer.parseInt(threadNumStr);
        } else {
            String message =
                    "The thread number in the file contains wrong data"
                    + threadNumStr;
            LOGGER.error(message);
            throw new DataHandlerException(message);
        }
    }
}

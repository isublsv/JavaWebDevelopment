package by.gartsmanovich.javawebdev.matrix.repository.impl;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.datahandler.DataReader;
import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;
import by.gartsmanovich.javawebdev.matrix.datahandler.impl.DataReaderImpl;
import by.gartsmanovich.javawebdev.matrix.datahandler.parser.DataParser;
import by.gartsmanovich.javawebdev.matrix.repository.Repository;
import by.gartsmanovich.javawebdev.matrix.repository.exception
        .RepositoryException;
import by.gartsmanovich.javawebdev.matrix.repository.specification
        .Specification;

public class MatrixRepository implements Repository {

    /**
     * The number of active threads to use.
     */
    private int threadNumber;

    /**
     * The array of values used to write to the main diagonal of the storage
     * array.
     */
    private int[] values;

    /**
     * The main storage that contains array of integers to fill by values.
     */
    private int[][] array;

    /**
     * Creates the matrix.
     *
     * @param path      the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public void createMatrix(final String path, final String delimiter) throws
            RepositoryException {

        DataReader reader = new DataReaderImpl();
        Matrix matrix;
        try {
            matrix = new DataParser().parseData(reader.readFile(path),
                                                delimiter);

            threadNumber = matrix.getThreadNumber();
            values = matrix.getDiagValues();
            array = matrix.getArr();

        } catch (DataHandlerException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    /**
     * Determines the business rules that applies to the matrix.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the 2-d array of integers that main diagonal was filled by
     * provided values.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public int[][] query(final Specification specification) throws
            RepositoryException {
        if (threadNumber != 0 || values != null || array != null) {
            return specification.specified(threadNumber, values, array);
        } else {
            throw new RepositoryException("Error during query execution!"
                                          + " The parameters does not exist");
        }
    }
}

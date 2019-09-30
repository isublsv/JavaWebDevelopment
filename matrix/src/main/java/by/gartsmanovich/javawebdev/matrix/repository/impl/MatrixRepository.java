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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixRepository implements Repository {

    /**
     * The logger for Toy Repository implementation class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            MatrixRepository.class);

    /**
     * The storage that contains entities to handle.
     */
    private Matrix matrix;


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

        try {
            matrix = new DataParser().parseData(reader.readFile(path),
                                                delimiter);
        } catch (DataHandlerException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    /**
     * Determines the business rules that applies to the matrix.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the matrix that main diagonal was filled by provided values.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public Matrix query(final Specification specification) throws
            RepositoryException {
        if (matrix != null) {
            return specification.specified(matrix);
        } else {
            LOGGER.error("Error during query execution! The matrix "
                         + "does not exist");
            throw new RepositoryException("Error during query execution!"
                                          + " The matrix does not exist");
        }
    }
}

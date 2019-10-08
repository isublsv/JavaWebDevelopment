package by.gartsmanovich.javawebdev.composite.repository.impl;

import by.gartsmanovich.javawebdev.composite.datahandler.DataReader;
import by.gartsmanovich.javawebdev.composite.datahandler.factory
        .DataHandlerFactory;
import by.gartsmanovich.javawebdev.composite.repository.Repository;
import by.gartsmanovich.javawebdev.composite.repository.exception
        .RepositoryException;
import by.gartsmanovich.javawebdev.composite.repository.specification
        .Specification;

/**
 * The repository interface implementation. Used to processing queries from
 * Services layer and applying to the matrix instance.
 *
 * @author Dmitry Gartsmanovich
 */
public class CompositeRepository implements Repository {

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

        DataHandlerFactory factory = DataHandlerFactory.getInstance();
        DataReader reader = factory.getDataReader();

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

        return null;
    }

    /**
     * Saves the last result obtained after executing the any method
     * that fill the main diagonal.
     *
     * @param path the path to storage file.
     * @throws RepositoryException if error happens during execution.
     */
    public void saveLastResult(final String path) throws RepositoryException {

    }
}

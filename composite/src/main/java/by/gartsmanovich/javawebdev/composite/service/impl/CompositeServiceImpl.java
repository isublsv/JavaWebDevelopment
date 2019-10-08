package by.gartsmanovich.javawebdev.composite.service.impl;

import by.gartsmanovich.javawebdev.composite.repository.Repository;
import by.gartsmanovich.javawebdev.composite.repository.factory
        .RepositoryFactory;
import by.gartsmanovich.javawebdev.composite.service.CompositeService;
import by.gartsmanovich.javawebdev.composite.service.exception
        .ServiceException;
import by.gartsmanovich.javawebdev.composite.service.validator.Validator;

/**
 * The matrix service interface implementation. Used to processing queries from
 * Controller layer, validating incoming parameters and passing them to the
 * Data
 * Access layer.
 *
 * @author Dmitry Gartsmanovich
 */
public class CompositeServiceImpl implements CompositeService {

    /**
     * Provides the access to Matrix repository class methods.
     */
    private Repository matrixRepository;

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * Constructs an implementation of Service application layer class
     * instance.
     */
    public CompositeServiceImpl() {
        RepositoryFactory factory = RepositoryFactory.getInstance();
        matrixRepository = factory.getCompositeRepository();
        validator = new Validator();
    }

    /**
     * Creates the Matrix instance. Gets the number of active threads, the
     * array of values for the main diagonal of the matrix and the values for
     * matrix array from file. Divide data in the file by provided delimiter.
     *
     * @param path      the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void createMatrix(final String path, final String delimiter) throws
            ServiceException {

    }

    /**
     * Returns the array of integers that main diagonal was filled by using
     * synchronised construction.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public int[][] fillBySeparateThreads() throws ServiceException {
        return null;
    }

    /**
     * Returns the array of integers that main diagonal was filled by using
     * Lock classes.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public int[][] fillByLocks() throws ServiceException {
        return null;
    }

    /**
     * Returns the array of integers that main diagonal was filled by using
     * Executor Service class.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public int[][] fillByExecutorService() throws ServiceException {
        return null;
    }

    /**
     * Returns the matrix that main diagonal was filled by using semaphore.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public int[][] fillBySemaphore() throws ServiceException {
        return null;
    }

    /**
     * Saves the last result obtained after executing the any method
     * that fill the main diagonal.
     *
     * @param path the path to storage file.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void saveLastResult(final String path) throws ServiceException {

    }
}

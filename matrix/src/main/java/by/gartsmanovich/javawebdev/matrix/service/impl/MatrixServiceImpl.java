package by.gartsmanovich.javawebdev.matrix.service.impl;

import by.gartsmanovich.javawebdev.matrix.repository.Repository;
import by.gartsmanovich.javawebdev.matrix.repository.exception
        .RepositoryException;
import by.gartsmanovich.javawebdev.matrix.repository.factory.RepositoryFactory;
import by.gartsmanovich.javawebdev.matrix.repository.specification.fill
        .FillByExecutorSpecification;
import by.gartsmanovich.javawebdev.matrix.repository.specification.fill.
        FillBySeparateThreadsSpecification;
import by.gartsmanovich.javawebdev.matrix.repository.specification.fill
        .FillBySemaphoreSpecification;
import by.gartsmanovich.javawebdev.matrix.repository.specification.fill.
        FillByLockSpecification;
import by.gartsmanovich.javawebdev.matrix.service.MatrixService;
import by.gartsmanovich.javawebdev.matrix.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.matrix.service.validator.Validator;

/**
 * The matrix service interface implementation. Used to processing queries from
 * Controller layer, validating incoming parameters and passing them to the Data
 * Access layer.
 *
 * @author Dmitry Gartsmanovich
 */
public class MatrixServiceImpl implements MatrixService {

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
    public MatrixServiceImpl() {
        RepositoryFactory factory = RepositoryFactory.getInstance();
        matrixRepository = factory.getMatrixRepository();
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
        try {
            if (!validator.isValidValue(path)) {
                throw new ServiceException("The parameters for creating matrix"
                                           + " are not valid");
            } else {
                matrixRepository.createMatrix(path, delimiter);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
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
        try {
            return matrixRepository
                    .query(new FillBySeparateThreadsSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
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
        try {
            return matrixRepository
                    .query(new FillByLockSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
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
        try {
            return matrixRepository.query(new FillByExecutorSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the matrix that main diagonal was filled by using semaphore.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public int[][] fillBySemaphore() throws ServiceException {
        try {
            return matrixRepository
                    .query(new FillBySemaphoreSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

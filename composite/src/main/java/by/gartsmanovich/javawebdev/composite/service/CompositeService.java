package by.gartsmanovich.javawebdev.composite.service;

import by.gartsmanovich.javawebdev.composite.service.exception.ServiceException;

/**
 * The interface used to determine the common structure of Service Layer
 * classes.
 *
 * @author Dmitry Gartsmanovich
 */
public interface CompositeService {

    /**
     * Creates the Matrix instance. Gets the number of active threads, the
     * array of values for the main diagonal of the matrix and the values for
     * matrix array from file. Divide data in the file by provided delimiter.
     *
     * @param path      the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @throws ServiceException if error happens during execution.
     */
    void createMatrix(String path, String delimiter) throws ServiceException;

    /**
     * Returns the array of integers that main diagonal was filled by threads
     * that was separate by indexes of the 2d array.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    int[][] fillBySeparateThreads() throws ServiceException;

    /**
     * Returns the array of integers that main diagonal was filled by using
     * Lock classes.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    int[][] fillByLocks() throws ServiceException;

    /**
     * Returns the array of integers that main diagonal was filled by using
     * Executor Service class.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    int[][] fillByExecutorService() throws ServiceException;

    /**
     * Returns the matrix that main diagonal was filled by using semaphore.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    int[][] fillBySemaphore() throws ServiceException;

    /**
     * Saves the last result obtained after executing the any method
     * that fill the main diagonal.
     *
     * @param path the path to storage file.
     * @throws ServiceException if error happens during execution.
     */
    void saveLastResult(String path) throws ServiceException;
}

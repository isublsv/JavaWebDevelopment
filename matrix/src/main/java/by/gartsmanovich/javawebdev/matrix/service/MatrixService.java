package by.gartsmanovich.javawebdev.matrix.service;

import by.gartsmanovich.javawebdev.matrix.service.exception.ServiceException;

public interface MatrixService {

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
     * Returns the array of integers that main diagonal was filled by using
     * synchronised construction.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    int[][] doOption1() throws ServiceException;

    /**
     * Returns the array of integers that main diagonal was filled by using
     * Lock classes.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    int[][] doOption2() throws ServiceException;

    /**
     * Returns the array of integers that main diagonal was filled by using
     * Executor Service class.
     *
     * @return the array of integers with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    int[][] doOption3() throws ServiceException;

    /**
     * Returns the matrix that main diagonal was filled by using synchronised
     * construction.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    int[][] doOption4() throws ServiceException;
}

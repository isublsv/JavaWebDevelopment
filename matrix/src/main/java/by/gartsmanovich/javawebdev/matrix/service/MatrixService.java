package by.gartsmanovich.javawebdev.matrix.service;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.service.exception.ServiceException;

public interface MatrixService {

    /**
     * Creates the Matrix instance. Fills it from file by integers that divided
     * by provided delimiter.
     *
     * @param path      the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @throws ServiceException if error happens during execution.
     */
    void createMatrix(String path, String delimiter) throws ServiceException;

    /**
     * Returns the matrix that main diagonal was filled by using synchronised
     * construction.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    Matrix doOption1() throws ServiceException;

    /**
     * Returns the matrix that main diagonal was filled by using Lock classes.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    Matrix doOption2() throws ServiceException;

    /**
     * Returns the matrix that main diagonal was filled by using synchronised
     * construction.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    Matrix doOption3() throws ServiceException;

    /**
     * Returns the matrix that main diagonal was filled by using synchronised
     * construction.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    Matrix doOption4() throws ServiceException;

    /**
     * Sets all elements under the main diagonal of the matrix to zero.
     *
     * @throws ServiceException if error happens during execution.
     */
    void resetMatrix() throws ServiceException;
}

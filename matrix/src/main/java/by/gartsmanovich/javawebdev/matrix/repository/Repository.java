package by.gartsmanovich.javawebdev.matrix.repository;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.repository.exception
        .RepositoryException;
import by.gartsmanovich.javawebdev.matrix.repository.specification.Specification;

public interface Repository {

    /**
     * Creates the matrix.
     *
     * @param path      the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @throws RepositoryException if error happens during execution.
     */
    void createMatrix(String path, String delimiter) throws
            RepositoryException;

    /**
     * Determines the business rules that applies to the matrix.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the matrix that main diagonal was filled by provided values.
     * @throws RepositoryException if error happens during execution.
     */
    Matrix query(Specification specification) throws RepositoryException;
}

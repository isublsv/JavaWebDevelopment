package by.gartsmanovich.javawebdev.matrix.repository.specification;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;

public interface Specification {

    /**
     * Applies specified criteria to the provided storage.
     *
     * @param matrix with provided entities.
     * @return the matrix that correspond to specified criteria,
     * false - otherwise.
     */
    Matrix specified(Matrix matrix);
}

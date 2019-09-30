package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.repository.specification.Specification;

public class ResetSpecification implements Specification {

    /**
     * Applies specified criteria to the provided storage.
     *
     * @param matrix with provided entities.
     * @return the matrix that correspond to specified criteria,
     * false - otherwise.
     */
    @Override
    public Matrix specified(final Matrix matrix) {
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            matrix.setElement(i, i, 0);
        }
        return matrix;
    }
}

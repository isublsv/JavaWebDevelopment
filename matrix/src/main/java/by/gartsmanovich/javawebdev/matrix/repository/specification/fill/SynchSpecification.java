package by.gartsmanovich.javawebdev.matrix.repository.specification.fill;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.bean.MatrixThread;
import by.gartsmanovich.javawebdev.matrix.repository.specification.Specification;

public class SynchSpecification implements Specification {

    /**
     * Applies specified criteria to the provided storage.
     *
     * @param matrix with provided entities.
     * @return the matrix that correspond to specified criteria,
     * false - otherwise.
     */
    @Override
    public Matrix specified(final Matrix matrix) {

        for (int i = 0; i < matrix.getThreadNumber(); i++) {
            Thread thread = new Thread(
                    new MatrixThread(matrix, matrix.getDiagInts()[i]));
            thread.start();
        }
        return matrix;
    }
}

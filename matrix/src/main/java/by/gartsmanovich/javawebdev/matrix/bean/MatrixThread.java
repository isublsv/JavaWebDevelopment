package by.gartsmanovich.javawebdev.matrix.bean;

public class MatrixThread implements Runnable {

    /**
     * Contains the matrix instance.
     */
    private Matrix matrix;

    /**
     * Comtains a new diagonal's value of the matrix.
     */
    private int value;

    /**
     * Constructs the new thread with specific parameters.
     *
     * @param matrixValue the matrix instance.
     * @param newValue    a new diagonal's value of the matrix.
     */
    public MatrixThread(final Matrix matrixValue, final int newValue) {
        matrix = matrixValue;
        value = newValue;
    }

    /**
     *
     */
    @Override
    public void run() {
        for (int i = 0; i <= matrix.getVerticalSize(); i++) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                synchronized (this) {
                    if (matrix.getElement(i, i) != 0) {
                        matrix.setElement(i, i, value);
                    }
                }
            }
        }
    }
}

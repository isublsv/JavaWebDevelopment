package by.gartsmanovich.javawebdev.matrix.bean;

public class MatrixThread implements Runnable {

    /**
     * Contains the array instance of integers.
     */
    private int[][] array;

    /**
     * Contains a new diagonal's value of the 2-d array.
     */
    private int value;

    /**
     * Constructs the new thread with specific parameters.
     *
     * @param arrayValue the array instance of integers.
     * @param newValue   a new diagonal's value of the 2-d array.
     */
    public MatrixThread(final int[][] arrayValue, final int newValue) {
        array = arrayValue;
        value = newValue;
    }

    /**
     * Finds the "empty" diagonal index in the provided array and sets the
     * provided value.
     */
    @Override
    public void run() {
        for (int i = 0; i <= array.length; i++) {
            if (array[i][i] == 0) {
                array[i][i] = value;
                break;
                }
        }
    }
}

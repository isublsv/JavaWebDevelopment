package by.gartsmanovich.javawebdev.matrix.bean;

public class Matrix {

    /**
     * Contains the multi-array of int values.
     */
    private int[][] arr;

    /**
     * Containds the number of active threads.
     */
    private int threadNumber;

    /**
     * Contains an array of diagonal values.
     */
    private int[] diagInts;

    /**
     * Constructs the square matrix instance.
     *
     * @param thread the number of active threads.
     * @param diag   the array of diagonal values.
     * @param ints      the row and column values.
     */
    public Matrix(final int thread, final int[] diag, final int[][] ints) {
        threadNumber = thread;
        diagInts = diag;
        arr = ints;
    }

    /**
     * Gets arr.
     *
     * @return value of arr.
     */
    public int[][] getArr() {
        return arr;
    }

    /**
     * Sets arr.
     *
     * @param arrValue value of arr.
     */
    public void setArr(final int[][] arrValue) {
        arr = arrValue;
    }

    /**
     * Gets threadNumber.
     *
     * @return value of threadNumber.
     */
    public int getThreadNumber() {
        return threadNumber;
    }

    /**
     * Sets threadNumber.
     *
     * @param threadNumberValue value of threadNumber.
     */
    public void setThreadNumber(final int threadNumberValue) {
        threadNumber = threadNumberValue;
    }

    /**
     * Gets diagValue.
     *
     * @return value of diagValue.
     */
    public int[] getDiagInts() {
        return diagInts;
    }

    /**
     * Sets diagValue.
     *
     * @param diagIntsValue value of diagValue.
     */
    public void setDiagInts(final int[] diagIntsValue) {
        diagInts = diagIntsValue;
    }

    /**
     * Returns the value vertical size of matrix.
     *
     * @return the value of vertical size of the matrix.
     */
    public int getVerticalSize() {
        return arr.length;
    }

    /**
     * Returns the value horizontal size of matrix.
     *
     * @return the value of horizontal size of the matrix.
     */
    int getHorizontalSize() {
        return arr[0].length;
    }

    /**
     * Gets the value of element.
     *
     * @param i the index of row in the matrix.
     * @param j the index of column in the matrix.
     * @return the value of element.
     */
    int getElement(final int i, final int j) {
        return arr[i][j];
    }

    /**
     * Sets the value of element.
     *
     * @param i     the index of row in the matrix.
     * @param j     the index of column in the matrix.
     * @param value the value of element.
     */
    public void setElement(final int i, final int j, final int value) {
        arr[i][j] = value;
    }

    /**
     * Represents an instance of an matrix as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(
                "\nMatrix : " + arr.length + "x" + arr[0].length + "\n");
        for (int[] row : arr) {
            for (int value : row) {
                s.append(value).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}

package by.gartsmanovich.javawebdev.matrix.bean;

public final class Matrix {

    /**
     * The lazy initialisation of matrix singleton instance.
     */
    private static Matrix instance = null;

    /**
     * Contains the multi-array of int values.
     */
    private int[][] arr;

    /**
     * Contains the number of active threads.
     */
    private int threadNumber;

    /**
     * Contains an array of diagonal values.
     */
    private int[] diagValues;

    /**
     * Constructs the square matrix instance.
     *
     * @param thread the number of active threads.
     * @param values the array of diagonal values.
     * @param ints   the row and column values.
     */
    private Matrix(final int thread, final int[] values, final int[][] ints) {
        threadNumber = thread;
        diagValues = values;
        arr = ints;
    }

    /**
     * The global method used to create only one synchronised matrix instance.
     *
     * @param thread the number of active threads.
     * @param values the array of diagonal values.
     * @param ints   the row and column values.
     * @return the matrix instance.
     */
    public static synchronized Matrix getInstance(final int thread,
            final int[] values, final int[][] ints) {

        if (instance == null) {
            instance = new Matrix(thread, values, ints);
        }
        return instance;
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
    public int[] getDiagValues() {
        return diagValues;
    }

    /**
     * Sets diagValue.
     *
     * @param diag value of diagValue.
     */
    public void setDiagValues(final int[] diag) {
        this.diagValues = diag;
    }
}

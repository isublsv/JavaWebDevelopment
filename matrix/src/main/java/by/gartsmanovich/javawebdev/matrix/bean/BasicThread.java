package by.gartsmanovich.javawebdev.matrix.bean;

/**
 * The super class of all thread classes. Stores the basic information about
 * all its implementations.
 */
public abstract class BasicThread {

    /**
     * The ID of the thread.
     */
    private int id;

    /**
     * The name of the thread.
     */
    private String name;

    /**
     * Contains the array instance of integers.
     */
    private int[][] array;

    /**
     * Contains a new values of diagonals.
     */
    private int[] values;

    /**
     * Constructs the thread with the name.
     *
     * @param idValue the ID of the thread.
     * @param nameValue the name of the thread.
     * @param arrayValue the array instance of integers.
     * @param diagValues the array instance with diagonal values.
     */
    public BasicThread(final int idValue, final String nameValue,
            final int[][] arrayValue, final int[] diagValues) {
        id = idValue;
        name = nameValue + " #" + idValue;
        array = arrayValue;
        values = diagValues;
    }

    /**
     * Gets id.
     *
     * @return value of id.
     */
    protected int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param idValue value of id.
     */
    public void setId(final int idValue) {
        id = idValue;
    }

    /**
     * Gets name.
     *
     * @return value of name.
     */
    protected String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param nameValue value of name.
     */
    public void setName(final String nameValue) {
        name = nameValue;
    }

    /**
     * Gets array.
     *
     * @return value of array.
     */
    public int[][] getArray() {
        return array;
    }

    /**
     * Sets array.
     *
     * @param arrayValue value of array.
     */
    public void setArray(final int[][] arrayValue) {
        array = arrayValue;
    }

    /**
     * Gets values.
     *
     * @return value of values.
     */
    public int[] getValues() {
        return values;
    }

    /**
     * Sets values.
     *
     * @param diagValues value of values.
     */
    public void setValues(final int[] diagValues) {
        values = diagValues;
    }
}

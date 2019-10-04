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
     * Constructs the thread with the name.
     *
     * @param idValue the ID of the thread.
     * @param nameValue the name of the thread.
     * @param arrayValue the array instance of integers.
     */
    public BasicThread(final int idValue, final String nameValue,
            final int[][] arrayValue) {
        id = idValue;
        name = nameValue + " #" + idValue;
        array = arrayValue;
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
}

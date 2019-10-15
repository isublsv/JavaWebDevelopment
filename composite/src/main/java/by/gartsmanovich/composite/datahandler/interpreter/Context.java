package by.gartsmanovich.composite.datahandler.interpreter;

import java.util.ArrayDeque;

/**
 * The class used to store initial data, result of the intermediate operations
 * and final data.
 *
 * @author Dmitry Gartsmanovich
 */
public class Context {

    /**
     * The deque used to store the different kind of intermediate data.
     */
    private ArrayDeque<Integer> contextValues = new ArrayDeque<>();

    /**
     * Removes and returns the first element of the deque.
     *
     * @return the value of the first element in the deque.
     */
    public Integer popValue() {
        return contextValues.pop();
    }

    /**
     * Inserts the element at the front of the deque.
     *
     * @param value the provided value to insert.
     */
    public void pushValue(final Integer value) {
        this.contextValues.push(value);
    }
}

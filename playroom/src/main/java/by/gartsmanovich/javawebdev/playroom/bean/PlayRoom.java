package by.gartsmanovich.javawebdev.playroom.bean;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;

import java.util.ArrayList;
import java.util.List;

public class PlayRoom {

    /**
     * The budget of a play room.
     */
    private double budget;

    /**
     * The path to file storage.
     */
    private String path;

    /**
     * The storage that contains an list of entities.
     */
    private List<Toy> toyStorage;

    /**
     * Constructs an instance of an entity with several parameters.
     *
     * @param budgetValue the budget of a play room.
     * @param pathValue the path to the file storage.
     */
    public PlayRoom(final double budgetValue, final String pathValue) {
        this.budget = budgetValue;
        this.path = pathValue;
        this.toyStorage = new ArrayList<>();
    }

    /**
     * Gets budget.
     *
     * @return value of budget.
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Sets budget.
     *
     * @param budgetValue value of budget.
     */
    public void setBudget(final double budgetValue) {
        this.budget = budgetValue;
    }

    /**
     * Gets path.
     *
     * @return value of path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param pathValue value of path.
     */
    public void setPath(final String pathValue) {
        path = pathValue;
    }

    /**
     * Gets toyStorage.
     *
     * @return value of toyStorage.
     */
    public List<Toy> getToyStorage() {
        return toyStorage;
    }
}

package by.gartsmanovich.javawebdev.playroom.bean;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;

import java.util.List;

public class PlayRoom {

    /**
     * The budget of a play room.
     */
    private double budget;

    /**
     * The storage that contains an list of entities.
     */
    private List<Toy> toyStorage;

    /**
     * Constructs an instance of an entity with several parameters.
     *
     * @param budgetValue the budget of a play room.
     * @param toyStorageValue the list of entities.
     */
    public PlayRoom(final double budgetValue, final List<Toy> toyStorageValue) {
        this.budget = budgetValue;
        this.toyStorage = toyStorageValue;
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
     * Sets toyStorage.
     *
     * @param toyStorageValue value of toyStorage.
     */
    public void setToyStorage(final List<Toy> toyStorageValue) {
        toyStorage = toyStorageValue;
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

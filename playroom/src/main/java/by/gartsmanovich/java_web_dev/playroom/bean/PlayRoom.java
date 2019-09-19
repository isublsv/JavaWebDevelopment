package by.gartsmanovich.java_web_dev.playroom.bean;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;

import java.util.ArrayList;
import java.util.List;

public final class PlayRoom {

    /**
     * Lazy initialisation of the Play Room Storage instance.
     */
    private static PlayRoom instance;

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
     */
    private PlayRoom(final double budgetValue) {
        this.budget = budgetValue;
        this.toyStorage = new ArrayList<>();
    }

    /**
     * Global point to createPlayRoom an instance of the storage.
     *
     * @param budgetValue the budget of a play room.
     * @return the instance of Play Room storage.
     */
    public static PlayRoom getInstance(final double
            budgetValue) {
        if (instance == null) {
            return new PlayRoom(budgetValue);
        }
        return instance;
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
     * Gets toyStorage.
     *
     * @return value of toyStorage.
     */
    public List<Toy> getToyStorage() {
        return toyStorage;
    }
}

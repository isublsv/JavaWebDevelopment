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
     * The title of a play room.
     */
    private String title;

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
     * @param titleValue  the title of a play room.
     * @param budgetValue the budget of a play room.
     */
    private PlayRoom(final String titleValue, final double budgetValue) {
        this.title = titleValue;
        this.budget = budgetValue;
        this.toyStorage = new ArrayList<>();
    }

    /**
     * Global point to createPlayRoom an instance of the storage.
     *
     * @param titleValue the title of a play room.
     * @param budgetValue the budget of a play room.
     * @return the instance of Play Room storage.
     */
    public static PlayRoom getInstance(final String titleValue, final double
            budgetValue) {
        if (instance == null) {
            return new PlayRoom(titleValue, budgetValue);
        }
        return instance;
    }

    /**
     * Gets title.
     *
     * @return value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param titleValue value of title.
     */
    public void setTitle(final String titleValue) {
        this.title = titleValue;
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

    /**
     * Represents an instance of an entity as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        return "PlayRoom{" + "title='" + title + ", " + "budget=" + budget
               + '}';
    }
}

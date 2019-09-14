package by.gartsmanovich.java_web_dev.playroom.bean;

public class PlayRoom extends Entity {

    /**
     * The title of a play room.
     */
    private String title;

    /**
     * The budget of a play room.
     */
    private double budget;

    /**
     * Constructs an instance of an entity with several parameters.
     *
     * @param idValue the identifier of a play room.
     * @param titleValue the title of a play room.
     * @param budgetValue the budget of a play room.
     */
    public PlayRoom(final long idValue, final String titleValue, final double
            budgetValue) {
        super(idValue);
        this.title = titleValue;
        this.budget = budgetValue;
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
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        PlayRoom playRoom = (PlayRoom) o;

        if (Double.compare(playRoom.budget, budget) != 0) {
            return false;
        }
        return title != null ? title.equals(playRoom.title) : playRoom.title
                == null;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = PRIME * result + (title != null ? title.hashCode() : 0);
        temp = Double.doubleToLongBits(budget);
        result = PRIME * result + (int) (temp ^ (temp >>> SHIFT));
        return result;
    }

    /**
     * Represents an instance of an entity as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        return "PlayRoom{" + "id=" + getId() + "title='" + title
                + ", budget=" + budget + '}';
    }
}

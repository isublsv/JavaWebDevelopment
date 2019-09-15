package by.gartsmanovich.java_web_dev.playroom.bean.toy;

import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;

public class ToyBall extends Toy {

    /**
     * The diameter of a toy ball.
     */
    private double diameter;

    /**
     * Default Toy ball constructor.
     */
    public ToyBall() {
    }

    /**
     * Constructs an instance of a toy ball with several parameters.
     *
     * @param idValue       the id of a toy ball.
     * @param titleValue    the title of a toy ball.
     * @param colorValue    the color of a toy ball.
     * @param ageValue      the age of a kid.
     * @param priceValue    the price of a toy ball.
     * @param diameterValue the diameter of a toy ball.
     */
    public ToyBall(final long idValue, final String titleValue, final Color
            colorValue, final int ageValue, final double priceValue, final
    double diameterValue) {
        super(idValue, titleValue, colorValue, ageValue, priceValue);
        this.diameter = diameterValue;
    }

    /**
     * Gets diameter.
     *
     * @return value of diameter.
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Sets diameter.
     *
     * @param diameterValue value of diameter.
     */
    public void setDiameter(final double diameterValue) {
        this.diameter = diameterValue;
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

        ToyBall toyBall = (ToyBall) o;

        return Double.compare(toyBall.diameter, diameter) == 0;
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
        temp = Double.doubleToLongBits(diameter);
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
        return "ToyBlock{" + "id=" + getId() + "title=" + getTitle() + ", "
                + "color=" + getColor() + ", age=" + getAge() + ", price="
                + getPrice() + ", " + "diameter=" + diameter + '}';
    }
}

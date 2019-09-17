package by.gartsmanovich.java_web_dev.playroom.bean.toy;

import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Size;

public class ToyCar extends Toy {

    /**
     * The size of a toy car.
     */
    private Size size;

    /**
     * Default Toy car constructor.
     */
    public ToyCar() {
        super();
    }

    /**
     * Constructs an instance of a toy car with several parameters.
     *
     * @param titleValue the title of a toy car.
     * @param colorValue the color of a toy car.
     * @param ageValue   the age of a kid.
     * @param priceValue the price of a toy car.
     * @param sizeValue  the size of a toy car.
     */
    public ToyCar(final String titleValue, final Color colorValue,
            final int ageValue, final double priceValue, final
            Size sizeValue) {
        super(titleValue, colorValue, ageValue, priceValue);
        this.size = sizeValue;
    }

    /**
     * Constructs an instance of a toy car with several parameters.
     *
     * @param idValue    the id of a toy car.
     * @param titleValue the title of a toy car.
     * @param colorValue the color of a toy car.
     * @param ageValue   the age of a kid.
     * @param priceValue the price of a toy car.
     * @param sizeValue  the size of a toy car.
     */
    public ToyCar(final long idValue, final String titleValue, final Color
            colorValue, final int ageValue, final double priceValue, final
            Size sizeValue) {
        super(idValue, titleValue, colorValue, ageValue, priceValue);
        this.size = sizeValue;
    }

    /**
     * Gets size.
     *
     * @return value of size.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param sizeValue value of size.
     */
    public void setSize(final Size sizeValue) {
        this.size = sizeValue;
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

        ToyCar toyCar = (ToyCar) o;

        return size == toyCar.size;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + (size != null ? size.hashCode() : 0);
        return result;
    }

    /**
     * Represents an instance of an entity as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        return String.format("%d, %s, %s, %d, %.2f, %s", getId(), getTitle(),
                getColor(), getAge(), getPrice(), size);
    }
}

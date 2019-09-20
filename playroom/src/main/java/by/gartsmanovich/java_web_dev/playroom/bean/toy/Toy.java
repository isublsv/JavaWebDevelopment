package by.gartsmanovich.java_web_dev.playroom.bean.toy;

import by.gartsmanovich.java_web_dev.playroom.bean.Entity;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;

public abstract class Toy extends Entity {

    /**
     * The title of a toy.
     */
    private String title;

    /**
     * The color of a toy.
     */
    private Color color;

    /**
     * The age of a kid.
     */
    private int age;

    /**
     * The price of a toy.
     */
    private double price;

    /**
     * Default constructor.
     */
    public Toy() {
        super();
    }

    /**
     * Constructs an instance of an entity with only ID parameter.
     *
     * @param idValue the ID value of a toy.
     */
    public Toy(final long idValue) {
        super(idValue);
    }

    /**
     * Constructs an instance of an entity with several parameters.
     *
     * @param idValue the ID value of a toy.
     * @param titleValue the title of a toy.
     * @param colorValue the color of a toy.
     * @param ageValue   the age of a kid.
     * @param priceValue the price of a toy.
     */
    public Toy(final long idValue, final String titleValue, final Color
            colorValue, final int ageValue, final double priceValue) {
        super(idValue);
        this.title = titleValue;
        this.color = colorValue;
        this.age = ageValue;
        this.price = priceValue;
    }

    /**
     * Constructs an instance of an entity with several parameters.
     *
     * @param titleValue the title of a toy.
     * @param colorValue the color of a toy.
     * @param ageValue   the age of a kid.
     * @param priceValue the price of a toy.
     */
    public Toy(final String titleValue, final Color
            colorValue, final int ageValue, final double priceValue) {
        super();
        this.title = titleValue;
        this.color = colorValue;
        this.age = ageValue;
        this.price = priceValue;
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
     * Gets color.
     *
     * @return value of color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param colorValue value of color.
     */
    public void setColor(final Color colorValue) {
        this.color = colorValue;
    }

    /**
     * Gets age.
     *
     * @return value of age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param ageValue value of age.
     */
    public void setAge(final int ageValue) {
        this.age = ageValue;
    }

    /**
     * Gets price.
     *
     * @return value of price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param priceValue value of price.
     */
    public void setPrice(final double priceValue) {
        this.price = priceValue;
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

        Toy toy = (Toy) o;

        if (age != toy.age) {
            return false;
        }
        if (Double.compare(toy.price, price) != 0) {
            return false;
        }
        if (title != null ? !title.equals(toy.title) : toy.title != null) {
            return false;
        }
        return color == toy.color;
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
        result = PRIME * result + (color != null ? color.hashCode() : 0);
        result = PRIME * result + age;
        temp = Double.doubleToLongBits(price);
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
        return getId() + ", " + title + ", " + color + ", " + age + ", "
                + price;
    }
}

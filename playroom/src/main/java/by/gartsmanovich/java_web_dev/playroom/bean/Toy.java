package by.gartsmanovich.java_web_dev.playroom.bean;

import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Material;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Size;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Title;

public class Toy {

    /**
     * The prime number for hashcode.
     */
    private static final int PRIME = 31;

    /**
     * The number for shifting double value to int value.
     */
    private static final int SHIFT = 32;
    /**
     * The title of a toy.
     */
    private Title title;

    /**
     * The size of a toy.
     */
    private Size size;

    /**
     * The color of a toy.
     */
    private Color color;

    /**
     * The material of a toy.
     */
    private Material material;

    /**
     * The price of a toy.
     */
    private double price;

    /**
     * Constructs an instance of a toy with several parameters.
     *
     * @param titleValue    the title of a toy.
     * @param sizeValue     the size of a toy.
     * @param colorValue    the color of a toy.
     * @param materialValue the material of a toy.
     * @param priceValue    the price of a toy.
     */
    public Toy(final Title titleValue, final Size sizeValue,
               final Color colorValue, final Material materialValue,
               final double priceValue) {
        this.title = titleValue;
        this.size = sizeValue;
        this.color = colorValue;
        this.material = materialValue;
        this.price = priceValue;
    }

    /**
     * Gets title.
     *
     * @return value of title.
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param titleValue value of title.
     */
    public void setTitle(final Title titleValue) {
        this.title = titleValue;
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
     * Gets material.
     *
     * @return value of material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets material.
     *
     * @param materialValue value of material.
     */
    public void setMaterial(final Material materialValue) {
        this.material = materialValue;
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
     *          argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Toy toy = (Toy) o;

        if (Double.compare(toy.price, price) != 0) {
            return false;
        }
        if (title != toy.title) {
            return false;
        }
        if (size != toy.size) {
            return false;
        }
        if (color != toy.color) {
            return false;
        }
        return material == toy.material;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = PRIME * result + (size != null ? size.hashCode() : 0);
        result = PRIME * result + (color != null ? color.hashCode() : 0);
        result = PRIME * result + (material != null ? material.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = PRIME * result + (int) (temp ^ (temp >>> SHIFT));
        return result;
    }
}

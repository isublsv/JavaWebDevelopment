package by.gartsmanovich.javawebdev.playroom.bean.builder;

import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.param.Size;
import by.gartsmanovich.javawebdev.playroom.bean.toy.ToyCar;

public class ToyCarBuilder {

    /**
     * The instance of a toy car.
     */
    private ToyCar toyCar;

    /**
     * Constructs an "empty" instance of a toy car entity.
     */
    public ToyCarBuilder() {
        this.toyCar = new ToyCar();
    }

    /**
     * Sets the title value for a toy car.
     *
     * @param titleValue the string value for a toy car
     * @return the builder instance
     */
    public ToyCarBuilder setTitle(final String titleValue) {
        toyCar.setTitle(titleValue);
        return this;
    }

    /**
     * Sets the color value for a toy car.
     *
     * @param colorValue the color value for a toy car.
     * @return the builder instance.
     */
    public ToyCarBuilder setColor(final Color colorValue) {
        toyCar.setColor(colorValue);
        return this;
    }

    /**
     * Sets the age value for a toy car.
     *
     * @param ageValue the age value for a toy car.
     * @return the builder instance.
     */
    public ToyCarBuilder setAge(final int ageValue) {
        toyCar.setAge(ageValue);
        return this;
    }

    /**
     * Sets the price for a toy car.
     *
     * @param priceValue the price value for a toy car.
     * @return the builder instance.
     */
    public ToyCarBuilder setPrice(final double priceValue) {
        toyCar.setPrice(priceValue);
        return this;
    }

    /**
     * Sets the size of a toy car.
     *
     * @param sizeValue the size value of a toy car.
     * @return the builder instance.
     */
    public ToyCarBuilder setSize(final Size sizeValue) {
        toyCar.setSize(sizeValue);
        return this;
    }

    /**
     * Returns the complete instance of a toy car.
     *
     * @return the toy car instance.
     */
    public ToyCar build() {
        return this.toyCar;
    }
}

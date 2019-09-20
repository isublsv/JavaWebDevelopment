package by.gartsmanovich.javawebdev.playroom.bean.builder;

import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.param.Material;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Doll;

public class DollBuilder {

    /**
     * The instance of a doll.
     */
    private Doll doll;

    /**
     * Constructs an "empty" instance of a doll entity.
     */
    public DollBuilder() {
        this.doll = new Doll();
    }

    /**
     * Sets the title value for a doll.
     *
     * @param titleValue the string value for a doll
     * @return the builder instance
     */
    public DollBuilder setTitle(final String titleValue) {
        doll.setTitle(titleValue);
        return this;
    }

    /**
     * Sets the color value for a doll.
     *
     * @param colorValue the color value for a doll.
     * @return the builder instance.
     */
    public DollBuilder setColor(final Color colorValue) {
        doll.setColor(colorValue);
        return this;
    }

    /**
     * Sets the age value for a doll.
     *
     * @param ageValue the age value for a doll.
     * @return the builder instance.
     */
    public DollBuilder setAge(final int ageValue) {
        doll.setAge(ageValue);
        return this;
    }

    /**
     * Sets the price for a doll.
     *
     * @param priceValue the price value for a doll.
     * @return the builder instance.
     */
    public DollBuilder setPrice(final double priceValue) {
        doll.setPrice(priceValue);
        return this;
    }

    /**
     * Sets the material of a doll.
     *
     * @param materialValue the material value of a doll.
     * @return the builder instance.
     */
    public DollBuilder setMaterial(final Material materialValue) {
        doll.setMaterial(materialValue);
        return this;
    }

    /**
     * Sets the weight of a doll.
     *
     * @param weightValue the weight value of a doll.
     * @return the builder instance.
     */
    public DollBuilder setWeight(final double weightValue) {
        doll.setWeight(weightValue);
        return this;
    }

    /**
     * Returns the complete instance of a doll.
     *
     * @return the doll instance.
     */
    public Doll build() {
        return this.doll;
    }
}

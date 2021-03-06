package by.gartsmanovich.javawebdev.playroom.bean.toy;

import by.gartsmanovich.javawebdev.playroom.bean.Entity;
import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.param.Material;

public class Doll extends Toy {

    /**
     * The material a doll.
     */
    private Material material;

    /**
     * The weight of a doll.
     */
    private double weight;

    /**
     * Default Doll constructor.
     */
    public Doll() {
        super();
    }

    /**
     * Constructs an instance of a doll with several parameters.
     *
     * @param titleValue    the title of a doll.
     * @param colorValue    the color of a doll.
     * @param ageValue      the age of a kid.
     * @param priceValue    the price of a doll.
     * @param materialValue the material of a doll.
     * @param weightValue   the weight of a doll.
     */
    public Doll(final String titleValue, final Color colorValue, final int
            ageValue, final double priceValue, final Material materialValue,
                final double weightValue) {
        super(titleValue, colorValue, ageValue, priceValue);
        this.material = materialValue;
        this.weight = weightValue;
    }

    /**
     * Constructs an instance of a doll with several parameters.
     *
     * @param idValue       the ID of a doll.
     * @param titleValue    the title of a doll.
     * @param colorValue    the color of a doll.
     * @param ageValue      the age of a kid.
     * @param priceValue    the price of a doll.
     * @param materialValue the material of a doll.
     * @param weightValue   the weight of a doll.
     */
    public Doll(final long idValue, final String titleValue, final Color
            colorValue, final int ageValue, final double priceValue, final
    Material materialValue, final double weightValue) {
        super(idValue, titleValue, colorValue, ageValue, priceValue);
        this.material = materialValue;
        this.weight = weightValue;
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
     * Gets weight.
     *
     * @return value of weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weightValue value of weight.
     */
    public void setWeight(final double weightValue) {
        this.weight = weightValue;
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

        Doll doll = (Doll) o;

        if (Double.compare(doll.weight, weight) != 0) {
            return false;
        }
        return material == doll.material;
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
        result = PRIME * result + (material != null ? material.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = PRIME * result + (int) (temp ^ (temp >>> Entity.SHIFT));
        return result;
    }

    /**
     * Represents an instance of an entity as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        return String.format("%d, %s, %s, %d, %.2f, %s, %.2f", getId(),
                getTitle(), getColor(), getAge(), getPrice(),
                material, weight);
    }
}

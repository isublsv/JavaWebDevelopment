package by.gartsmanovich.webparsing.bean.impl;

import by.gartsmanovich.webparsing.bean.PackageType;
import by.gartsmanovich.webparsing.bean.param.Powder;

/**
 * The class describes the package type for powder drugs.
 *
 * @author Dmitry Gartsmanovich
 */
public class PowderPackage extends PackageType {

    /**
     * The package type for powder drugs.
     */
    private Powder type;

    /**
     * The weight of the package.
     */
    private int weight;

    /**
     * Default constructor.
     */
    public PowderPackage() {
    }

    /**
     * Constructs the instance with specific parameters.
     *
     * @param priceValue  the price of the powder drug.
     * @param typeValue   the package type of the powder drug.
     * @param weightValue the weight of the package.
     */
    public PowderPackage(final double priceValue, final Powder typeValue,
            final int weightValue) {
        super(priceValue);
        type = typeValue;
        weight = weightValue;
    }

    /**
     * Gets type.
     *
     * @return value of type.
     */
    public Powder getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param typeValue value of type.
     */
    public void setType(final Powder typeValue) {
        type = typeValue;
    }

    /**
     * Gets weight.
     *
     * @return value of weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weightValue value of weight.
     */
    public void setWeight(final int weightValue) {
        weight = weightValue;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + type.hashCode();
        result = PRIME * result + weight;
        return result;
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

        PowderPackage that = (PowderPackage) o;

        if (weight != that.weight) {
            return false;
        }
        return type == that.type;
    }
}

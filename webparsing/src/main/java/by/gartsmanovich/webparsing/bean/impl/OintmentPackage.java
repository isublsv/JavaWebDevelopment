package by.gartsmanovich.webparsing.bean.impl;

import by.gartsmanovich.webparsing.bean.PackageType;
import by.gartsmanovich.webparsing.bean.param.Ointment;

/**
 * The class describes the package type for ointment drugs.
 *
 * @author Dmitry Gartsmanovich
 */
public class OintmentPackage extends PackageType {

    /**
     * The package type for ointment drugs.
     */
    private Ointment type;

    /**
     * The weight of the package.
     */
    private int weight;

    /**
     * The concentration of the drug.
     */
    private int concentration;

    /**
     * Constructs the instance with specific parameters.
     *
     * @param priceValue         the price of the ointment drug.
     * @param typeValue          the package type of the ointment drug.
     * @param weightValue        the weight of the package.
     * @param concentrationValue the concentration of the drug.
     */
    public OintmentPackage(final double priceValue, final Ointment typeValue,
            final int weightValue, final int concentrationValue) {
        super(priceValue);
        type = typeValue;
        weight = weightValue;
        concentration = concentrationValue;
    }

    /**
     * Gets type.
     *
     * @return value of type.
     */
    public Ointment getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param typeValue value of type.
     */
    public void setType(final Ointment typeValue) {
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
     * Gets concentration.
     *
     * @return value of concentration.
     */
    public int getConcentration() {
        return concentration;
    }

    /**
     * Sets concentration.
     *
     * @param concentrationValue value of concentration.
     */
    public void setConcentration(final int concentrationValue) {
        concentration = concentrationValue;
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
        result = PRIME * result + concentration;
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

        OintmentPackage that = (OintmentPackage) o;

        if (weight != that.weight) {
            return false;
        }
        if (concentration != that.concentration) {
            return false;
        }
        return type == that.type;
    }
}

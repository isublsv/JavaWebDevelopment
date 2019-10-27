package by.gartsmanovich.webparsing.bean.impl;

import by.gartsmanovich.webparsing.bean.PackageType;
import by.gartsmanovich.webparsing.bean.param.Liquid;

/**
 * The class describes the package type for liquid drugs.
 *
 * @author Dmitry Gartsmanovich
 */
public class LiquidPackage extends PackageType {

    /**
     * The package type for liquid drugs.
     */
    private Liquid type;

    /**
     * The volume of the package.
     */
    private int volume;

    /**
     * Constructs the instance with specific parameters.
     *
     * @param priceValue  the price of the liquid drug.
     * @param typeValue   the package type of the liquid drug.
     * @param volumeValue the volume of the package.
     */
    public LiquidPackage(final double priceValue, final Liquid typeValue,
            final int volumeValue) {
        super(priceValue);
        type = typeValue;
        volume = volumeValue;
    }

    /**
     * Gets type.
     *
     * @return value of type.
     */
    public Liquid getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param typeValue value of type.
     */
    public void setType(final Liquid typeValue) {
        type = typeValue;
    }

    /**
     * Gets volume.
     *
     * @return value of volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Sets volume.
     *
     * @param volumeValue value of volume.
     */
    public void setVolume(final int volumeValue) {
        volume = volumeValue;
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
        result = PRIME * result + volume;
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

        LiquidPackage that = (LiquidPackage) o;

        if (volume != that.volume) {
            return false;
        }
        return type == that.type;
    }
}

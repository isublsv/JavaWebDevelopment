package by.gartsmanovich.webparsing.bean.impl;

import by.gartsmanovich.webparsing.bean.PackageType;
import by.gartsmanovich.webparsing.bean.param.Pack;

/**
 * The class describes the package type for tablet drugs.
 *
 * @author Dmitry Gartsmanovich
 */
public class TabletsPackage extends PackageType {

    /**
     * The package type for tablet drugs.
     */
    private Pack type;

    /**
     * The quantity of tablet in the package.
     */
    private int quantity;

    /**
     * Constructs the instance with specific parameters.
     *
     * @param priceValue    the price of the tablet drug.
     * @param typeValue     the package type of the tablet drug.
     * @param quantityValue the quantity of tablet in the package.
     */
    public TabletsPackage(final double priceValue, final Pack typeValue,
            final int quantityValue) {
        super(priceValue);
        type = typeValue;
        quantity = quantityValue;
    }

    /**
     * Gets type.
     *
     * @return value of type.
     */
    public Pack getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param typeValue value of type.
     */
    public void setType(final Pack typeValue) {
        type = typeValue;
    }

    /**
     * Gets quantity.
     *
     * @return value of quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantityValue value of quantity.
     */
    public void setQuantity(final int quantityValue) {
        quantity = quantityValue;
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
        result = PRIME * result + quantity;
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

        TabletsPackage that = (TabletsPackage) o;

        if (quantity != that.quantity) {
            return false;
        }
        return type == that.type;
    }
}

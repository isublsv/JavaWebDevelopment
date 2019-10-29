package by.gartsmanovich.webparsing.bean;

/**
 * The super class of all Package classes. Stores the basic information about
 * all its implementations.
 *
 * @author Dmitry Gartsmanovich
 */
public abstract class PackageType {

    /**
     * The prime number for hashcode.
     */
    protected static final int PRIME = 31;

    /**
     * The number for shifting double value to int value.
     */
    private static final int SHIFT = 32;

    /**
     * The price of the package.
     */
    private double price;

    /**
     * Default constructor.
     */
    public PackageType() {
    }

    /**
     * Constructs the package with the price.
     *
     * @param priceValue the price of the package.
     */
    public PackageType(final double priceValue) {
        price = priceValue;
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
        price = priceValue;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(price);
        return (int) (temp ^ (temp >>> SHIFT));
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

        PackageType that = (PackageType) o;

        return Double.compare(that.price, price) == 0;
    }

    /**
     * Represents an instance of an entity as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        return String.format("PackageType{price=%s ", price);
    }
}

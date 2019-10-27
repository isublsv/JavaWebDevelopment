package by.gartsmanovich.webparsing.bean.param;

/**
 * Enum-class represents the pack types for powder that application supports.
 *
 * @author Dmitry Gartsmanovich
 */
public enum Powder {

    /**
     * The singleton instance for the plastic bottle type instance.
     * This has the numeric value of {@code 0}.
     */
    PLASTIC_BOTTLE,

    /**
     * The singleton instance for the aluminium bag type instance.
     * This has the numeric value of {@code 1}.
     */
    ALUMINIUM_BAG,

    /**
     * The singleton instance for the sachet type instance.
     * This has the numeric value of {@code 2}.
     */
    SACHET;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

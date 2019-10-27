package by.gartsmanovich.webparsing.bean.param;

/**
 * Enum-class represents the pack types for tablets that application supports.
 *
 * @author Dmitry Gartsmanovich
 */
public enum Pack {

    /**
     * The singleton instance for the plastic bottle type instance.
     * This has the numeric value of {@code 0}.
     */
    PLASTIC_BOTTLE,

    /**
     * The singleton instance for the blister pack type instance.
     * This has the numeric value of {@code 1}.
     */
    BLISTER_PACK,

    /**
     * The singleton instance for the plastic case type instance.
     * This has the numeric value of {@code 2}.
     */
    PLASTIC_CASE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

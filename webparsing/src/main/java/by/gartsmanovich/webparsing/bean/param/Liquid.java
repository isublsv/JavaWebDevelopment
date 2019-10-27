package by.gartsmanovich.webparsing.bean.param;

/**
 * Enum-class represents the pack types for liquid that application supports.
 *
 * @author Dmitry Gartsmanovich
 */
public enum Liquid {

    /**
     * The singleton instance for the drop counter type instance.
     * This has the numeric value of {@code 0}.
     */
    DROP_COUNTER,

    /**
     * The singleton instance for the ampule type instance.
     * This has the numeric value of {@code 1}.
     */
    AMPULE,

    /**
     * The singleton instance for the glass bottle type instance.
     * This has the numeric value of {@code 2}.
     */
    GLASS_BOTTLE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

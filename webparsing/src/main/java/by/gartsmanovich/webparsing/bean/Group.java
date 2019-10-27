package by.gartsmanovich.webparsing.bean;

/**
 * Enum-class represents the group list of medicines that application supports.
 *
 * @author Dmitry Gartsmanovich
 */
public enum Group {

    /**
     * The singleton instance for the analgesics group instance.
     * This has the numeric value of {@code 0}.
     */
    ANALGESICS,

    /**
     * The singleton instance for the antibiotics group instance.
     * This has the numeric value of {@code 1}.
     */
    ANTIBIOTICS,

    /**
     * The singleton instance for the antiseptics group instance.
     * This has the numeric value of {@code 2}.
     */
    ANTISEPTICS,

    /**
     * The singleton instance for the hormones group instance.
     * This has the numeric value of {@code 3}.
     */
    HORMONES,

    /**
     * The singleton instance for the stimulants group instance.
     * This has the numeric value of {@code 4}.
     */
    STIMULANTS,

    /**
     * The singleton instance for the vitamins group instance.
     * This has the numeric value of {@code 5}.
     */
    VITAMINS;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

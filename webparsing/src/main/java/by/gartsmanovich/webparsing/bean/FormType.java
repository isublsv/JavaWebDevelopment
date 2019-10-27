package by.gartsmanovich.webparsing.bean;

/**
 * Enum-class represents the form types of drugs that application supports.
 *
 * @author Dmitry Gartsmanovich
 */
public enum FormType {

    /**
     * The singleton instance for the tablets type instance.
     * This has the numeric value of {@code 0}.
     */
    TABLETS,

    /**
     * The singleton instance for the drops type instance.
     * This has the numeric value of {@code 1}.
     */
    DROPS,

    /**
     * The singleton instance for the powder type instance.
     * This has the numeric value of {@code 2}.
     */
    POWDER,

    /**
     * The singleton instance for the ointment type instance.
     * This has the numeric value of {@code 3}.
     */
    OINTMENT;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

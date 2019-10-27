package by.gartsmanovich.webparsing.bean.param;

/**
 * Enum-class represents the pack types for ointment that application supports.
 *
 * @author Dmitry Gartsmanovich
 */
public enum  Ointment {

    /**
     * The singleton instance for the tube type instance.
     * This has the numeric value of {@code 0}.
     */
    TUBE,

    /**
     * The singleton instance for the ointment package type instance.
     * This has the numeric value of {@code 1}.
     */
    OINTMENT_PACKAGE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

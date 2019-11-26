package by.gartsmanovich.hitcher.bean;

/**
 * Enum-class represents the list of statuses defined in the system.
 *
 * @author Dmitry Gartsmanovich
 */
public enum Status {

    /**
     * The singleton instance for the banned status.
     * This has the numeric value of {@code 0}.
     */
    BANNED,

    /**
     * The singleton instance for the active status.
     * This has the numeric value of {@code 1}.
     */
    ACTIVE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

package by.gartsmanovich.hitcher.bean;

/**
 * Enum-class represents the list of roles defined in the system.
 *
 * @author Dmitry Gartsmanovich
 */
public enum Role {

    /**
     * The singleton instance for the admin.
     * This has the numeric value of {@code 0}.
     */
    ADMIN,

    /**
     * The singleton instance for the user.
     * This has the numeric value of {@code 1}.
     */
    USER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

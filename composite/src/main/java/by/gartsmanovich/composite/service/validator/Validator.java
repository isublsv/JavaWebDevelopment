package by.gartsmanovich.composite.service.validator;

/**
 * Utility class used to validate provided data.
 *
 * @author Dmitry Gartsmanovich
 */
public class Validator {

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final String value) {
        return value == null || value.isEmpty();
    }
}

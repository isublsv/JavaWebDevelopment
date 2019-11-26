package by.gartsmanovich.hitcher.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class used to validate provided data.
 *
 * @author Dmitry Gartsmanovich
 */
public class ServiceValidator {

    /**
     * Describes a pattern that validates user email value.
     */
    private static final Pattern EMAIL_REGEX = Pattern
            .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                     Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user login value.
     */
    private static final Pattern LOGIN_REGEX = Pattern
            .compile("^[A-Z0-9._\\-]{4,45}$", Pattern.CASE_INSENSITIVE);

    /**
     * Validates user email.
     *
     * @param email the provided user email.
     * @return true if email value is valid, false - otherwise.
     */
    public boolean isValidEmail(final String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return isValidValue(email) && matcher.find();
    }

    /**
     * Validates user login.
     *
     * @param login the provided user login.
     * @return true if login value is valid, false - otherwise.
     */
    public boolean isValidLogin(final String login) {
        Matcher matcher = LOGIN_REGEX.matcher(login);
        return isValidValue(login) && matcher.find();
    }

    /**
     * Validates a provided parameter.
     *
     * @param param the provided value.
     * @return true if parameter is valid, false - otherwise.
     */
    private boolean isValidValue(final String param) {
        return param != null && !param.isEmpty();
    }
}

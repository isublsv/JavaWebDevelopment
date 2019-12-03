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
    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user login value.
     */
    private static final Pattern LOGIN_REGEX = Pattern.compile(
            "^[A-Z0-9._\\-]{4,45}$", Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user password value.
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     */
    //anna aaZZa44@
    private static final Pattern PASS_REGEX = Pattern.compile(
          "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    /**
     * Describes a pattern that validates user name value.
     */
    private static final Pattern NAME_REGEX = Pattern.compile(
            "^[A-ZА-ЯЎІ]{2,20}$", Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user surname value.
     */
    private static final Pattern SURNAME_REGEX = Pattern.compile(
            "^[A-ZА-ЯЎІ]{2,60}$", Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user patronymic value.
     */
    private static final Pattern PATRONYMIC_REGEX = Pattern.compile(
            "^[A-ZА-ЯЎІ]{0,60}$", Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user phone value.
     */
    private static final Pattern PHONE_REGEX = Pattern.compile(
            "[+]\\d{1,3}\\s[(]\\d{2,3}[)]\\s\\d{3}[\\-]\\d{2}[\\-]\\d{2}",
            Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user address value.
     */
    private static final Pattern ADDRESS_REGEX = Pattern.compile(
            "^[A-ZА-ЯЎІ\\-,.\\d /]{0,100}$", Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user address value.
     */
    private static final Pattern PREFERENCES_REGEX = Pattern.compile(
            "[1-3]", Pattern.CASE_INSENSITIVE);

    /**
     * Validates user email.
     *
     * @param email the provided user email.
     * @return true if email value is valid, false - otherwise.
     */
    public boolean isValidEmail(final String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.find();
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
     * Validates user name.
     *
     * @param name the provided user name.
     * @return true if name value is valid, false - otherwise.
     */
    public boolean isValidName(final String name) {
        Matcher matcher = NAME_REGEX.matcher(name);
        return isValidValue(name) && matcher.find();
    }

    /**
     * Validates user name.
     *
     * @param surname the provided user surname.
     * @return true if surname value is valid, false - otherwise.
     */
    public boolean isValidSurname(final String surname) {
        Matcher matcher = SURNAME_REGEX.matcher(surname);
        return isValidValue(surname) && matcher.find();
    }

    /**
     * Validates user patronymic.
     *
     * @param patronymic the provided user patronymic.
     * @return true if patronymic value is valid, false - otherwise.
     */
    public boolean isValidPatronymic(final String patronymic) {
        Matcher matcher = PATRONYMIC_REGEX.matcher(patronymic);
        return matcher.find();
    }

    /**
     * Validates user phoneNumber.
     *
     * @param phoneNumber the provided user phoneNumber.
     * @return true if phoneNumber value is valid, false - otherwise.
     */
    public boolean isValidPhone(final String phoneNumber) {
        Matcher matcher = PHONE_REGEX.matcher(phoneNumber);
        return matcher.find();
    }

    /**
     * Validates user address.
     *
     * @param address the provided user address.
     * @return true if address value is valid, false - otherwise.
     */
    public boolean isValidAddress(final String address) {
        Matcher matcher = ADDRESS_REGEX.matcher(address);
        return matcher.find();
    }

    /**
     * Validates user preferences.
     *
     * @param preferences the provided user preferences.
     * @return true if preferences values is valid, false - otherwise.
     */
    public boolean isValidPreferences(final String preferences) {
        Matcher matcher = PREFERENCES_REGEX.matcher(preferences);
        try {
            Integer.parseInt(preferences);
            return matcher.find();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates user preferences.
     *
     * @param pass the provided user preferences.
     * @return true if preferences values is valid, false - otherwise.
     */
    public boolean isValidPassword(final String pass) {
        Matcher matcher = PASS_REGEX.matcher(pass);
        return matcher.find();
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

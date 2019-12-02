package by.gartsmanovich.hitcher.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

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
        return isNull(patronymic) || matcher.find();
    }

    /**
     * Validates user phoneNumber.
     *
     * @param phoneNumber the provided user phoneNumber.
     * @return true if phoneNumber value is valid, false - otherwise.
     */
    public boolean isValidPhone(final String phoneNumber) {
        Matcher matcher = PHONE_REGEX.matcher(phoneNumber);
        return isNull(phoneNumber) || matcher.find();
    }

    /**
     * Validates user address.
     *
     * @param address the provided user address.
     * @return true if address value is valid, false - otherwise.
     */
    public boolean isValidAddress(final String address) {
        Matcher matcher = ADDRESS_REGEX.matcher(address);
        return isNull(address) || matcher.find();
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
     * Validates a provided parameter.
     *
     * @param param the provided value.
     * @return true if parameter is valid, false - otherwise.
     */
    private boolean isValidValue(final String param) {
        return param != null && !param.isEmpty();
    }

}

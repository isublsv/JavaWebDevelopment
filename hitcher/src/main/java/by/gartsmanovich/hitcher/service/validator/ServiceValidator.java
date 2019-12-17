package by.gartsmanovich.hitcher.service.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
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
            "^[A-Z0-9._%+\\-]+@[A-Z0-9.\\-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user login value.
     */
    private static final Pattern LOGIN_REGEX = Pattern.compile(
            "^[\\w]{4,45}$", Pattern.CASE_INSENSITIVE);

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
    private static final Pattern SURNAME_PATRONYMIC_REGEX = Pattern.compile(
            "^[A-ZА-ЯЎІ]{0,60}$", Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user patronymic value.
     */
    private static final Pattern OTHER_VALUES_REGEX = Pattern.compile(
            "^[A-ZА-Я ,.]{0,60}$", Pattern.CASE_INSENSITIVE);

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
    private static final Pattern PREFERENCES_REGEX =
            Pattern.compile("[1-3]", Pattern.CASE_INSENSITIVE);

    /**
     * Describes a pattern that validates user driver license number value.
     */
    private static final Pattern DRIVER_LICENSE_REGEX = Pattern.compile(
            "^\\d[A-Z]{2} [\\d]{6}$");

    /**
     * Describes a pattern that validates numbers.
     */
    private static final Pattern NUMBERS_REGEX = Pattern.compile("[\\d]+");

    /**
     * Describes a pattern that validates decimal.
     */
    private static final Pattern DECIMAL_REGEX =
            Pattern.compile("^\\d*\\.?\\d*$");

    /**
     * Validates user email.
     *
     * @param email the provided user email.
     * @return true if email value is valid, false - otherwise.
     */
    public boolean isValidEmail(final String email) {
        if (Objects.nonNull(email)) {
            Matcher matcher = EMAIL_REGEX.matcher(email);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user login.
     *
     * @param login the provided user login.
     * @return true if login value is valid, false - otherwise.
     */
    public boolean isValidLogin(final String login) {
        if (Objects.nonNull(login)) {
            Matcher matcher = LOGIN_REGEX.matcher(login);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user name.
     *
     * @param name the provided user name.
     * @return true if name value is valid, false - otherwise.
     */
    public boolean isValidName(final String name) {
        if (Objects.nonNull(name)) {
            Matcher matcher = NAME_REGEX.matcher(name);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user name.
     *
     * @param surname the provided user surname.
     * @return true if surname value is valid, false - otherwise.
     */
    public boolean isValidSurname(final String surname) {
        if (Objects.nonNull(surname)) {
            Matcher matcher = SURNAME_PATRONYMIC_REGEX.matcher(surname);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user patronymic.
     *
     * @param patronymic the provided user patronymic.
     * @return true if patronymic value is valid, false - otherwise.
     */
    public boolean isValidPatronymic(final String patronymic) {
        if (Objects.nonNull(patronymic)) {
            Matcher matcher = SURNAME_PATRONYMIC_REGEX.matcher(patronymic);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user phoneNumber.
     *
     * @param phoneNumber the provided user phoneNumber.
     * @return true if phoneNumber value is valid, false - otherwise.
     */
    public boolean isValidPhone(final String phoneNumber) {
        if (Objects.nonNull(phoneNumber)) {
            Matcher matcher = PHONE_REGEX.matcher(phoneNumber);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user address.
     *
     * @param address the provided user address.
     * @return true if address value is valid, false - otherwise.
     */
    public boolean isValidAddress(final String address) {
        if (Objects.nonNull(address)) {
            Matcher matcher = ADDRESS_REGEX.matcher(address);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user preferences.
     *
     * @param preferences the provided user preferences.
     * @return true if preferences values is valid, false - otherwise.
     */
    public boolean isValidPreferences(final String preferences) {
        if (Objects.nonNull(preferences)) {
            Matcher matcher = PREFERENCES_REGEX.matcher(preferences);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user password.
     *
     * @param pass the provided user password.
     * @return true if password values is valid, false - otherwise.
     */
    public boolean isValidPassword(final String pass) {
        if (Objects.nonNull(pass)) {
            Matcher matcher = PASS_REGEX.matcher(pass);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates user driver license.
     *
     * @param license the provided user driver license.
     * @return true if driver license values is valid, false - otherwise.
     */
    public boolean isValidLicense(final String license) {
        if (Objects.nonNull(license)) {
            Matcher matcher = DRIVER_LICENSE_REGEX.matcher(license);
            return matcher.find();
        }
        return false;
    }

    /**
     * Validates provided string parameters.
     *
     * @param param the provided values.
     * @return true if parameters are valid, false - otherwise.
     */
    public boolean isValidValues(final String... param) {
        for (String value : param) {
            if (Objects.nonNull(value)) {
                Matcher matcher = OTHER_VALUES_REGEX.matcher(value);
                if (!matcher.find()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates provided numeric parameters.
     *
     * @param numbers the provided numeric parameters.
     * @return true if numeric parameters are valid, false - otherwise.
     */
    public boolean isValidNumbers(final String... numbers) {
        for (String value : numbers) {
            if (Objects.nonNull(value)) {
                Matcher matcher = NUMBERS_REGEX.matcher(value);
                if (!matcher.find()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates provided date value.
     *
     * @param value the provided date.
     * @return true if date is valid, false - otherwise.
     */
    public boolean isValidDate(final String value) {
        try {
            if (Objects.nonNull(value)) {
                LocalDate date = LocalDate.parse(value);
                if (date.isBefore(LocalDate.now())) {
                    return false;
                }
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Validates provided decimal numbers.
     *
     * @param decimals the provided decimal numbers.
     * @return true if decimal numbers are valid, false - otherwise.
     */
    public boolean isValidDecimal(final String... decimals) {
        for (String value : decimals) {
            if (Objects.nonNull(value)) {
                Matcher matcher = DECIMAL_REGEX.matcher(value);
                if (!matcher.find()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if provided date of departure is before the date of arrival.
     *
     * @param departure the provided date of trip departure.
     * @param arrival   the provided date of trip arrival.
     * @return true if date of departure is before the date of arrival, false
     * otherwise.
     */
    public boolean isValidDates(final LocalDate departure,
            final LocalDate arrival) {
        return departure.isBefore(arrival);
    }
}

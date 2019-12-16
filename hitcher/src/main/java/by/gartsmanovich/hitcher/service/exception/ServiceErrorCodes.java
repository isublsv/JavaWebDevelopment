package by.gartsmanovich.hitcher.service.exception;

/**
 * Class contains the list of error codes that exist in web-app. Each code has
 * numeric value and text message.
 *
 * @author Dmitry Gartsmanovich
 */
public enum ServiceErrorCodes {

    /**
     * The singleton instance for the "driver exist" code.
     * This has the numeric value of {@code 0}.
     */
    DRIVER_EXIST("User dont have a driver license"),

    /**
     * The singleton instance for the "email exist" code.
     * This has the numeric value of {@code 1}.
     */
    EMAIL_EXISTS("User with that email is already exists"),

    /**
     * The singleton instance for the "invalid address" code.
     * This has the numeric value of {@code 2}.
     */
    INVALID_ADDRESS("Address value is not valid"),

    /**
     * The singleton instance for the "invalid city values" code.
     * This has the numeric value of {@code 3}.
     */
    INVALID_CITY_VALUES("Destination points are invalid"),

    /**
     * The singleton instance for the "invalid current password" code.
     * This has the numeric value of {@code 4}.
     */
    INVALID_CURRENT_PASS("Current password value is not valid"),

    /**
     * The singleton instance for the "invalid date format" code.
     * This has the numeric value of {@code 5}.
     */
    INVALID_DATE_FORMAT("Date format or value are invalid"),

    /**
     * The singleton instance for the "invalid driver information" code.
     * This has the numeric value of {@code 6}.
     */
    INVALID_DRIVER_INFO("Driver info are not valid"),

    /**
     * The singleton instance for the "invalid email" code.
     * This has the numeric value of {@code 7}.
     */
    INVALID_EMAIL("Email value is not valid"),

    /**
     * The singleton instance for the "invalid login" code.
     * This has the numeric value of {@code 8}.
     */
    INVALID_LOGIN("Login value is not valid"),

    /**
     * The singleton instance for the "invalid login or password" code.
     * This has the numeric value of {@code 9}.
     */
    INVALID_LOGIN_OR_PASS("Login or password are not valid"),

    /**
     * The singleton instance for the "invalid name" code.
     * This has the numeric value of {@code 10}.
     */
    INVALID_NAME("Name value is not valid"),

    /**
     * The singleton instance for the "invalid parameters number" code.
     * This has the numeric value of {@code 11}.
     */
    INVALID_PARAMETERS_NUMBER("Parameters number are not valid"),

    /**
     * The singleton instance for the "invalid password" code.
     * This has the numeric value of {@code 12}.
     */
    INVALID_PASS("Password value is not valid"),

    /**
     * The singleton instance for the "invalid patronymic value" code.
     * This has the numeric value of {@code 13}.
     */
    INVALID_PATRONYMIC("Patronymic value is not valid"),

    /**
     * The singleton instance for the "invalid hone format" code.
     * This has the numeric value of {@code 14}.
     */
    INVALID_PHONE_NUMBER("Phone number value is not valid"),

    /**
     * The singleton instance for the "invalid preferences values" code.
     * This has the numeric value of {@code 15}.
     */
    INVALID_PREFERENCES("Preferences values are not valid"),

    /**
     * The singleton instance for the "invalid surname value" code.
     * This has the numeric value of {@code 16}.
     */
    INVALID_SURNAME("Surname value is not valid"),

    /**
     * The singleton instance for the "invalid values" code.
     * This has the numeric value of {@code 17}.
     */
    INVALID_PARAMETER_VALUE("Parameter values are not valid"),

    /**
     * The singleton instance for the "sql error" code.
     * This has the numeric value of {@code 18}.
     */
    SQL_ERROR("SQL Error"),

    /**
     * The singleton instance for the "user does not exist" code.
     * This has the numeric value of {@code 19}.
     */
    USER_DOES_NOT_EXIST("User does not exist"),

    /**
     * The singleton instance for the "user exists" code.
     * This has the numeric value of {@code 20}.
     */
    USER_EXISTS("User with that username is already exists"),

    /**
     * The singleton instance for the "user not found" code.
     * This has the numeric value of {@code 21}.
     */
    USER_NOT_FOUND("User not found"),

    /**
     * The singleton instance for the "wrong login or password" code.
     * This has the numeric value of {@code 22}.
     */
    WRONG_LOGIN_OR_PASS("Wrong login or password"),

    /**
     * The singleton instance for the "trip not found" code.
     * This has the numeric value of {@code 23}.
     */
    TRIP_NOT_FOUND("Trip not found"),

    /**
     * The singleton instance for the "passenger list" code.
     * This has the numeric value of {@code 24}.
     */
    PASSENGER_LIST_NOT_EMPTY("Passenger list is not empty");

    /**
     * Contains the message value.
     */
    private String message;

    /**
     * Constructs ServiceErrorCodes instance.
     *
     * @param messageValue the provided message.
     */
    ServiceErrorCodes(final String messageValue) {
        this.message = messageValue;
    }

    /**
     * Gets code.
     *
     * @return value of code.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns numeric code value.
     *
     * @return code numeric value.
     */
    public String getCodeValue() {
        return String.valueOf(this.ordinal());
    }
}

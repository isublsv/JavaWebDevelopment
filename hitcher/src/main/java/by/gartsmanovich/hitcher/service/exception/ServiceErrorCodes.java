package by.gartsmanovich.hitcher.service.exception;

/**
 * @author Dmitry Gartsmanovich
 */
public enum ServiceErrorCodes {

    INVALID_LOGIN_OR_PASS("Login or password are not valid"),

    USER_DOES_NOT_EXIST("User does not exist"),

    USER_NOT_FOUND("User not found"),

    INVALID_ADDRESS("Address value is not valid"),

    INVALID_PHONE_NUMBER("Phone number value is not valid"),

    INVALID_EMAIL("Email value is not valid"),

    INVALID_PATRONYMIC("Patronymic value is not valid"),

    INVALID_SURNAME("Surname value is not valid"),

    INVALID_LOGIN("Login value is not valid"),

    INVALID_PASS("Password value is not valid"),

    INVALID_CURRENT_PASS("Current password value is not valid"),

    INVALID_NAME("Name value is not valid"),

    INVALID_PREFERENCES("Preferences values are not valid"),

    INVALID_DRIVER_INFO("Driver info are not valid"),

    INVALID_CITY_VALUES("Destination points are invalid"),

    INVALID_DATE_FORMAT("Date format or value are invalid"),

    INVALID_VALUES("Values are not valid"),

    INVALID_PARAMETERS_NUMBER("Parameters number are not valid"),
    
    USER_EXISTS("User with that username is already exists"),

    EMAIL_EXISTS("User with that email is already exists"),
    
    SQL_ERROR("SQL Error"),

    /**
     *
     */
    WRONG_LOGIN_OR_PASS("Wrong login or password");
    
    /**
     *
     */
    private String message;

    /**
     * @param messageValue
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
     * @return
     */
    public int getCode() {
        return this.ordinal();
    }
}

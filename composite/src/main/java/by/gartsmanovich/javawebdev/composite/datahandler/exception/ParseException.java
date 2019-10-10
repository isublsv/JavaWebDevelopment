package by.gartsmanovich.javawebdev.composite.datahandler.exception;

/**
 * The Parse Exception class indicates that abnormal condition
 * had occurred during parse processing.
 *
 * @author Dmitry Gartsmanovich
 */
public class ParseException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public ParseException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ParseException(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).
     */
    public ParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).
     */
    public ParseException(final Throwable cause) {
        super(cause);
    }
}

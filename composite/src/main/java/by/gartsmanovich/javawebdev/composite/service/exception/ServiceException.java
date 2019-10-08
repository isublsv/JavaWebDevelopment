package by.gartsmanovich.javawebdev.composite.service.exception;

/**
 * The Service Exception class indicates that abnormal condition had occurred in
 * the Service application layer.
 *
 * @author Dmitry Gartsmanovich
 */
public class ServiceException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public ServiceException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ServiceException(final String message) {
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
    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }
}

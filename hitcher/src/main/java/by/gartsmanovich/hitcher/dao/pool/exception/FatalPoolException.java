package by.gartsmanovich.hitcher.dao.pool.exception;

/**
 * The Fatal Pool Exception class indicates that abnormal condition had
 * occurred during the connection pool creation.
 *
 * @author Dmitry Gartsmanovich
 */
public class FatalPoolException extends RuntimeException {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public FatalPoolException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public FatalPoolException(final String message) {
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
    public FatalPoolException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).
     */
    public FatalPoolException(final Throwable cause) {
        super(cause);
    }
}

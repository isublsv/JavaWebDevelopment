package by.gartsmanovich.hitcher.dao.pool.exception;

/**
 * The Pool Exception class indicates that abnormal condition had
 * occurred during pool connection execution.
 *
 * @author Dmitry Gartsmanovich
 */
public class PoolException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public PoolException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public PoolException(final String message) {
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
    public PoolException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).
     */
    public PoolException(final Throwable cause) {
        super(cause);
    }
}

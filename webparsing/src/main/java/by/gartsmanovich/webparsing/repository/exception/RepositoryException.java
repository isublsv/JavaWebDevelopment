package by.gartsmanovich.webparsing.repository.exception;

/**
 * The Repository Exception class indicates that abnormal condition had
 * occurred in the Data Access Object application layer.
 *
 * @author Dmitry Gartsmanovich
 */
public class RepositoryException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public RepositoryException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public RepositoryException(final String message) {
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
    public RepositoryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).
     */
    public RepositoryException(final Throwable cause) {
        super(cause);
    }
}

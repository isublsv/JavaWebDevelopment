package by.gartsmanovich.java_web_dev.playroom.dao.exception;

/**
 * The DAOException class indicates that abnormal condition had occurred in
 * the Data Access Object application layer.
 */
public class DAOException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public DAOException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public DAOException(final String message) {
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
    public DAOException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).
     */
    public DAOException(final Throwable cause) {
        super(cause);
    }
}

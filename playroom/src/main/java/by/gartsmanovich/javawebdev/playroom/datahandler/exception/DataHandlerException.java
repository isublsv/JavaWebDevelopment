package by.gartsmanovich.javawebdev.playroom.datahandler.exception;

/**
 * The DataHandlerException Exception class indicates that abnormal condition
 * had occurred in the Data Access Object application layer.
 */
public class DataHandlerException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public DataHandlerException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public DataHandlerException(final String message) {
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
    public DataHandlerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).
     */
    public DataHandlerException(final Throwable cause) {
        super(cause);
    }
}

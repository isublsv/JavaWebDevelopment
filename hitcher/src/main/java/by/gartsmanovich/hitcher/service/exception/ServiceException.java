package by.gartsmanovich.hitcher.service.exception;

/**
 * The Service Exception class indicates that abnormal condition had
 * occurred in
 * the Service application layer.
 *
 * @author Dmitry Gartsmanovich
 */
public class ServiceException extends Exception {

    /**
     * Error code value.
     */
    private final ServiceErrorCodes code;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     *
     * @param codeValue the provided error code value.
     */
    public ServiceException(final ServiceErrorCodes codeValue) {
        super();
        this.code = codeValue;
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message   the detail message.
     * @param codeValue the provided error code value.
     */
    public ServiceException(final String message,
            final ServiceErrorCodes codeValue) {
        super(message);
        this.code = codeValue;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * @param message   the detail message (which is saved for later retrieval
     *                  by the {@link #getMessage()} method).
     * @param cause     the cause (which is saved for later retrieval by the
     *                  {@link #getCause()} method).
     * @param codeValue the provided error code value.
     */
    public ServiceException(final String message, final Throwable cause,
            final ServiceErrorCodes codeValue) {
        super(message, cause);
        this.code = codeValue;
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message.
     *
     * @param cause     the cause (which is saved for later retrieval by the
     *                  {@link #getCause()} method).
     * @param codeValue the provided error code value.
     */
    public ServiceException(final Throwable cause,
            final ServiceErrorCodes codeValue) {
        super(cause);
        this.code = codeValue;
    }

    /**
     * Gets code.
     *
     * @return value of code.
     */
    public ServiceErrorCodes getErrorCode() {
        return code;
    }
}

package by.gartsmanovich.java_web_dev.playroom.service.validator;

public final class Validator {

    /**
     * The Validator instance will be created at the start of the execution.
     */
    private static final Validator INSTANCE = new Validator();

    private Validator() {

    }

    /**
     * Global point for access to parser methods.
     *
     * @return the instance of Data Parser.
     */
    public static Validator getInstance() {
        return INSTANCE;
    }

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final double value) {
        return value > 0 && value < Double.MAX_VALUE;
    }

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final long value) {
        return value > 0 && value < Long.MAX_VALUE;
    }

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final String value) {
        return value != null && !value.isEmpty();
    }

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final char value) {
        return Character.isLetter(value);
    }

    /**
     * Checks if string array has valid data.
     *
     * @param strings the string array.
     * @return true if values are valid, false - otherwise.
     */
    public boolean isValidEntityParams(final String[] strings) {
        return false;
    }

    /**
     * Checks if the values are valid.
     *
     * @param startId the first value to valid.
     * @param endId   the second value to valid.
     * @return true if values are valid, false - otherwise.
     */
    public boolean isValidValue(final long startId, final long endId) {
        long diff = endId - startId;
        return (startId > 0) && (startId < Long.MAX_VALUE) && (endId > 0)
                && (endId < Long.MAX_VALUE) && (diff > 0);

    }
}

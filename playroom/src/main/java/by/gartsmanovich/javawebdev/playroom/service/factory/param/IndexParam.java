package by.gartsmanovich.javawebdev.playroom.service.factory.param;

public enum IndexParam {

    /**
     * The singleton instance for the title parameter.
     * This has the numeric value of {@code 0}.
     */
    PARAM_TITLE,

    /**
     * The singleton instance for the color parameter.
     * This has the numeric value of {@code 1}.
     */
    PARAM_COLOR,

    /**
     * The singleton instance for the age parameter.
     * This has the numeric value of {@code 2}.
     */
    PARAM_AGE,

    /**
     * The singleton instance for the price parameter.
     * This has the numeric value of {@code 3}.
     */
    PARAM_PRICE,

    /**
     * The singleton instance for the first additional parameter.
     * This has the numeric value of {@code 4}.
     */
    PARAM_ADD_1,

    /**
     * The singleton instance for the second additional parameter.
     * This has the numeric value of {@code 5}.
     */
    PARAM_ADD_2;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

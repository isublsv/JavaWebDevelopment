package by.gartsmanovich.java_web_dev.playroom.bean.param;

public enum  Size {

    /**
     * The singleton instance for the big size of a toy car.
     * This has the numeric value of {@code 1}.
     */
    BIG,

    /**
     * The singleton instance for the medium size of a toy car.
     * This has the numeric value of {@code 2}.
     */
    MEDIUM,

    /**
     * The singleton instance for the small size of a toy car.
     * This has the numeric value of {@code 3}.
     */
    SMALL;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

package by.gartsmanovich.java_web_dev.playroom.bean.param;

public enum Color {

    /**
     * The singleton instance for the blue color of a toy.
     * This has the numeric value of {@code 1}.
     */
    BLUE,

    /**
     * The singleton instance for the green color of a toy.
     * This has the numeric value of {@code 2}.
     */
    GREEN,

    /**
     * The singleton instance for the red color of a toy.
     * This has the numeric value of {@code 3}.
     */
    RED,

    /**
     * The singleton instance for the orange color of a toy.
     * This has the numeric value of {@code 4}.
     */
    ORANGE,

    /**
     * The singleton instance for the purple color of a toy.
     * This has the numeric value of {@code 5}.
     */
    PURPLE,

    /**
     * The singleton instance for the white color of a toy.
     * This has the numeric value of {@code 6}.
     */
    WHITE,

    /**
     * The singleton instance for the yellow color of a toy.
     * This has the numeric value of {@code 7}.
     */
    YELLOW,

    /**
     * The singleton instance for the multicoloured type of a toy.
     * This has the numeric value of {@code 8}.
     */
    MULTICOLORED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

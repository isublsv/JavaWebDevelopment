package by.gartsmanovich.javawebdev.playroom.bean.param;

public enum Color {

    /**
     * The singleton instance for the blue color of a toy.
     * This has the numeric value of {@code 0}.
     */
    BLUE,

    /**
     * The singleton instance for the green color of a toy.
     * This has the numeric value of {@code 1}.
     */
    GREEN,

    /**
     * The singleton instance for the red color of a toy.
     * This has the numeric value of {@code 2}.
     */
    RED,

    /**
     * The singleton instance for the orange color of a toy.
     * This has the numeric value of {@code 3}.
     */
    ORANGE,

    /**
     * The singleton instance for the purple color of a toy.
     * This has the numeric value of {@code 4}.
     */
    PURPLE,

    /**
     * The singleton instance for the white color of a toy.
     * This has the numeric value of {@code 5}.
     */
    WHITE,

    /**
     * The singleton instance for the yellow color of a toy.
     * This has the numeric value of {@code 6}.
     */
    YELLOW,

    /**
     * The singleton instance for the multicoloured type of a toy.
     * This has the numeric value of {@code 7}.
     */
    MULTICOLORED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

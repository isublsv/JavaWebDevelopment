package by.gartsmanovich.java_web_dev.playroom.bean.param;

public enum Material {

    /**
     * The singleton instance for the plastic material of a toy.
     * This has the numeric value of {@code 1}.
     */
    PLASTIC,

    /**
     * The singleton instance for the wood material of a toy.
     * This has the numeric value of {@code 2}.
     */
    WOOD,

    /**
     * The singleton instance for the rubber material of a toy.
     * This has the numeric value of {@code 3}.
     */
    RUBBER;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

package by.gartsmanovich.javawebdev.playroom.bean.param;

public enum BlockType {

    /**
     * The singleton instance for the Lego toy block.
     * This has the numeric value of {@code 0}.
     */
    LEGO,

    /**
     * The singleton instance for the Unit toy block.
     * This has the numeric value of {@code 1}.
     */
    UNIT,

    /**
     * The singleton instance for the Kapla toy block.
     * This has the numeric value of {@code 2}.
     */
    KAPLA;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

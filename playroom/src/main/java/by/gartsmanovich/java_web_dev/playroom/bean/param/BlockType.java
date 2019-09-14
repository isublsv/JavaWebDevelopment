package by.gartsmanovich.java_web_dev.playroom.bean.param;

public enum BlockType {

    /**
     * The singleton instance for the Lego toy block.
     * This has the numeric value of {@code 1}.
     */
    LEGO,

    /**
     * The singleton instance for the Unit toy block.
     * This has the numeric value of {@code 2}.
     */
    UNIT,

    /**
     * The singleton instance for the Kapla toy block.
     * This has the numeric value of {@code 3}.
     */
    KAPLA;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

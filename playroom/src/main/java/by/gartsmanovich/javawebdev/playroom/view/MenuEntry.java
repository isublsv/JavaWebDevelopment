package by.gartsmanovich.javawebdev.playroom.view;

public class MenuEntry {

    /**
     * The title of the menu entry.
     */
    private String title;

    /**
     * Constructs the menu entry instance with a title.
     *
     * @param titleValue the title value.
     */
    MenuEntry(final String titleValue) {
        this.title = titleValue;
    }

    /**
     * Gets title.
     *
     * @return value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param titleValue value of title.
     */
    public void setTitle(final String titleValue) {
        this.title = titleValue;
    }
}

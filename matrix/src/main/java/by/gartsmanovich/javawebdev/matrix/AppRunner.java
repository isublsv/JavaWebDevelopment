package by.gartsmanovich.javawebdev.matrix;

import by.gartsmanovich.javawebdev.matrix.view.Menu;

/**
 * Class used to start the application.
 *
 * @author Dmitry Gartsmanovich
 */
public final class AppRunner {

    /**
     * Private constructor.
     */
    private AppRunner() {
    }

    /**
     * Starts the application.
     *
     * @param args the initial parameters for the application.
     */
    public static void main(final String[] args) {

        Menu menu = new Menu();

        menu.run();
    }
}

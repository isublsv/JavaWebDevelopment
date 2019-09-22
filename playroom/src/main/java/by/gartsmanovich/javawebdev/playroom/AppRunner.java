package by.gartsmanovich.javawebdev.playroom;

import by.gartsmanovich.javawebdev.playroom.view.Menu;

public final class AppRunner {

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

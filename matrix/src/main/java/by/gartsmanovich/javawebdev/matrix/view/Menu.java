package by.gartsmanovich.javawebdev.matrix.view;

import by.gartsmanovich.javawebdev.matrix.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    /**
     * The logger for Menu class.
     */
    private static final Logger LOGGER = LogManager.getLogger(Menu.class);

    /**
     * The instance used to access to the Controller application layer.
     */
    private Controller controller;
    /**
     * The pattern describes the main view of the menu entry.
     */
    private static final String MENU_PATTERN = "%s - %s\n";

    /**
     * The list of menu entries.
     */
    private List<MenuEntry> entries = new ArrayList<>();

    /**
     * Sets the exit from the application.
     */
    private boolean isExit = false;

    /**
     * Constructs menu of the application.
     */
    public Menu() {
        controller = new Controller();
        fillMenu();
    }

    /**
     * Starts the menu.
     */
    public void run() {
        while (!isExit) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\\Z");
            String query = scanner.nextLine();

            if (!query.isEmpty() && query.startsWith("EXIT")) {
                isExit = true;
            }

            String response = controller.executeTask(query);

            LOGGER.debug(response);
        }
    }

    /**
     * Adds entry to menu.
     *
     * @param entry the menu entry to add.
     */
    private void addEntry(final MenuEntry entry) {
        entries.add(entry);
    }

    private void printMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("\nMenu:\n");
        builder.append("Type \"COMMAND_NAME PATTERN\" to run the command:\n");

        for (int i = 0; i < entries.size(); i++) {
            MenuEntry entry = entries.get(i);
            String entryFormatted = String.format(
                    MENU_PATTERN, (i + 1), entry.getTitle());
            builder.append(entryFormatted);
        }

        String menu = builder.toString();
        LOGGER.debug(menu);
    }

    private void fillMenu() {
        addEntry(new MenuEntry("CREATE_MATRIX. Pattern: path delimiter."
                            + " Example: \"CREATE_MATRIX data\\input.txt  \""));
        addEntry(new MenuEntry("THREAD_DISTRIBUTION"));
        addEntry(new MenuEntry("LOCK"));
        addEntry(new MenuEntry("EXECUTOR_SERVICE"));
        addEntry(new MenuEntry("SEMAPHORE"));
        addEntry(new MenuEntry("EXIT"));
    }
}

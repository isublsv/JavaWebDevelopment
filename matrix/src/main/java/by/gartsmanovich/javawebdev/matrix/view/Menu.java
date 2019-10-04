package by.gartsmanovich.javawebdev.matrix.view;

import by.gartsmanovich.javawebdev.matrix.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class used to display the menu of the application.
 */
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

            if (!query.isEmpty() && (query.startsWith("6")
                                     || query.startsWith("Exit"))) {
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

    /**
     * Prints the menu to the log.
     */
    private void printMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("\nMenu:\n");
        builder.append("Type \"Number of the command pattern\" to run:\n");

        for (int i = 0; i < entries.size(); i++) {
            MenuEntry entry = entries.get(i);
            String entryFormatted = String.format(
                    MENU_PATTERN, (i + 1), entry.getTitle());
            builder.append(entryFormatted);
        }

        String menu = builder.toString();
        LOGGER.debug(menu);
    }

    /**
     * Fills the menu tih options that describes the behavior of the
     * application.
     */
    private void fillMenu() {
        addEntry(new MenuEntry("Create the matrix. Pattern: path delimiter."
                            + " Example: \"1 data\\input.txt  \""));
        addEntry(new MenuEntry("Fill the matrix using index distribution."));
        addEntry(new MenuEntry("Fill the matrix using concurrent locks."));
        addEntry(new MenuEntry("Fill the matrix using executor service."));
        addEntry(new MenuEntry("Fill the matrix using semaphore."));
        addEntry(new MenuEntry("Exit."));
    }
}

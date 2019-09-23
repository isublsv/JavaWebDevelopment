package by.gartsmanovich.javawebdev.playroom.view;

import by.gartsmanovich.javawebdev.playroom.controller.Controller;
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
                String[] args = query.trim().split(" ");
                if (args.length == 2) {
                    isExit = true;
                }
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

        builder.append("\nMenu:\nBefore any pattern type the MENU_NAME,"
                       + " please!\n");
        builder.append(
                "The doll type: title color age price material weight\n");
        builder.append("The ball type: title color age price diameter\n");
        builder.append(
                "The block type: title color age price blockType material\n");
        builder.append("The car type: title color age price size\n");

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
        addEntry(new MenuEntry("CREATE_PLAYROOM. Pattern: budget "
                               + "path_to_filename delimiter."));
        addEntry(new MenuEntry("ADD_TOY. Pattern: title color age price"
                               + " additional_option1 additional_option2."));
        addEntry(new MenuEntry("UPDATE_TOY. Pattern: ID title color age price"
                               + " additional_option1 additional_option2."));
        addEntry(new MenuEntry("REMOVE_TOY. Pattern: ID"));
        addEntry(new MenuEntry("FIND_TOY_BY_ID. Pattern: ID"));
        addEntry(new MenuEntry("FIND_TOY_BY_TITLE. Pattern: title"));
        addEntry(new MenuEntry("FIND_TOYS_BY_FIRST_TITLE_LETTER."
                               + " Pattern: first_letter"));
        addEntry(new MenuEntry(
                "FIND_TOYS_BY_RANGE_ID. Pattern: " + "startID endID"));
        addEntry(new MenuEntry("SORT_BY_AGE"));
        addEntry(new MenuEntry("SORT_BY_COLOR_AND_PRICE"));
        addEntry(new MenuEntry("PRINT_ALL"));
        addEntry(new MenuEntry("EXIT"));
    }
}

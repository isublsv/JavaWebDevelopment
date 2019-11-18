package by.gartsmanovich.hitcher.controller.command.manager;

import java.util.ResourceBundle;

/**
 * Utility class is used to manage the page list in the Controller layer of the
 * application.
 *
 * @author Dmitry Gartsmanovich
 */
public final class ConfigurationManager {

    /**
     * Specific resource bundle contains the path to the property file with
     * page list.
     */
    private static final ResourceBundle RESOURCE_BUNDLE =
            ResourceBundle.getBundle(
            "config");

    /**
     * The private constructor. Forbids the implicit creation of the
     * MessageManager class instance.
     */
    private ConfigurationManager() {
    }

    /**
     * Returns the value for the specified key from the property file.
     *
     * @param key the key for the desired page.
     * @return the string for the given key.
     */
    public static String getProperty(final String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}

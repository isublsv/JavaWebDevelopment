package by.gartsmanovich.composite.controller.command.manager;

import java.util.ResourceBundle;

/**
 * Utility class used to manage the messages in the Controller layer of the
 * application.
 *
 * @author Dmitry Gartsmanovich
 */
public final class MessageManager {

    /**
     * Specific resource bundle contains the path to the property file with
     * messages.
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle("messages");

    /**
     * The private constructor. Forbids the implicit creation of the
     * MessageManager class instance.
     */
    private MessageManager() {
    }

    /**
     * Returns the value for the specified key from the property file.
     *
     * @param key the key for the desired message.
     * @return the string for the given key.
     */
    public static String getProperty(final String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}

package by.gartsmanovich.java_web_dev.playroom.controller.command.manager;

import java.util.ResourceBundle;

public final class MessageManager {

    /**
     * Specific resource bundle contains the path to the property file with
     * messages.
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle("resource.messages");

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

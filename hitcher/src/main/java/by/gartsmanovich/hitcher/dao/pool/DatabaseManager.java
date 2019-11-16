package by.gartsmanovich.hitcher.dao.pool;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Utility class used to get Connection pool properties from provided file.
 *
 * @author Dmitry Gartsmanovich
 */
final class DatabaseManager {

    /**
     * Represents the url of the database.
     */
    static final String DB_URL = "url";

    /**
     * Represents the username of user to access to the database.
     */
    private static final String DB_USER = "user";

    /**
     * Represents the user password to access to the database.
     */
    private static final String DB_PASS = "password";

    /**
     * Represents the encoding of the database.
     */
    private static final String DB_ENCODING = "characterEncoding";

    /**
     * Represents the value if the database can support unicode.
     */
    private static final String DB_UNICODE = "useUnicode";

    /**
     * Represents the value if the database can support autoReconnect.
     */
    private static final String DB_RECONNECT = "autoReconnect";

    /**
     * Represents the pool size.
     */
    static final String DB_POOL_SIZE = "poolSize";

    /**
     * Represents the poll timeout.
     */
    static final String DB_TIMEOUT = "pollTimeout";

    /**
     * Specific resource bundle contains the path to the file with
     * database properties.
     */
    private static final ResourceBundle RESOURCE_BUNDLE
            = ResourceBundle.getBundle("database");

    /**
     * The private constructor. Forbids the implicit creation of the
     * MessageManager class instance.
     */
    private DatabaseManager() {
    }

    /**
     * Creates properties file that contains parameters of database connection
     * pool.
     *
     * @return the list of properties.
     */
    static Properties getDataBaseProperties() {
        Properties properties = new Properties();
        properties.put(DB_USER, getProperty(DB_USER));
        properties.put(DB_PASS, getProperty(DB_PASS));
        properties.put(DB_ENCODING, getProperty(DB_ENCODING));
        properties.put(DB_UNICODE, getProperty(DB_UNICODE));
        properties.put(DB_RECONNECT, getProperty(DB_RECONNECT));
        return properties;
    }

    /**
     * Returns the value for the specified key from the property file.
     *
     * @param key the key for the desired message.
     * @return the string for the given key.
     */
    static String getProperty(final String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}

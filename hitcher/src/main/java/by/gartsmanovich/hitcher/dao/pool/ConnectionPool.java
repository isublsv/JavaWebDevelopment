package by.gartsmanovich.hitcher.dao.pool;

import by.gartsmanovich.hitcher.dao.pool.exception.FatalPoolException;
import by.gartsmanovich.hitcher.dao.pool.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.gartsmanovich.hitcher.dao.pool.DatabaseManager.DB_POOL_SIZE;
import static by.gartsmanovich.hitcher.dao.pool.DatabaseManager.DB_TIMEOUT;
import static by.gartsmanovich.hitcher.dao.pool.DatabaseManager.DB_URL;
import static by.gartsmanovich.hitcher.dao.pool.DatabaseManager
        .getDataBaseProperties;
import static by.gartsmanovich.hitcher.dao.pool.DatabaseManager.getProperty;

/**
 * Connection pool class for web application.
 *
 * @author Dmitry Gartsmanovich
 */
public final class ConnectionPool {

    /**
     * The logger for ConnectionPool class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ConnectionPool.class);

    /**
     * Maximum size of available connections.
     */
    private int poolSize;

    /**
     * Maximum time in milliseconds which provides to user to take available
     * connection.
     */
    private int poolTimeout;

    /**
     * Lazy initialisation of class instance.
     */
    private static ConnectionPool instance;

    /**
     * Used to synchronize pool connections creation.
     */
    private static Lock lock = new ReentrantLock();

    /**
     * Checks if an instance was created.
     */
    private static AtomicBoolean isCreated = new AtomicBoolean(false);

    /**
     * Deque for available connections.
     */
    private BlockingDeque<PooledConnection> availableConnections;

    /**
     * Deque for used connections.
     */
    private BlockingDeque<PooledConnection> usedConnections;

    /**
     * Instantiate connection pool.
     *
     * @throws FatalPoolException if cannot access to database.
     */
    private ConnectionPool() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            String message = "Cannot access to database.";
            LOGGER.fatal(message, e);
            throw new FatalPoolException(message, e);
        }
        init();
    }

    /**
     * Creates (if not exists) and returns threadsafe connection pool instance.
     *
     * @return threadsafe connection pool instance.
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Initialises connection pool parameters.
     */
    private void init() {
        Properties properties = getDataBaseProperties();

        poolSize = Integer.parseInt(getProperty(DB_POOL_SIZE));
        poolTimeout = Integer.parseInt(getProperty(DB_TIMEOUT));

        availableConnections = new LinkedBlockingDeque<>(poolSize);
        usedConnections = new LinkedBlockingDeque<>(poolSize);

        createConnection(properties);

        if (availableConnections.size() != poolSize) {
            String message =
                    "Incorrect number of connections were created.";
            LOGGER.fatal(message);
            throw new FatalPoolException(message);
        }
    }

    /**
     * Creates connections and adds them to the pool.
     *
     * @param properties the list of database parameters.
     */
    private void createConnection(final Properties properties) {
        final String jdbcUrl = getProperty(DB_URL);
        for (int i = 0; i < poolSize; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(jdbcUrl, properties);
                PooledConnection pooledConnection = new PooledConnection(
                        connection);
                availableConnections.offer(pooledConnection);
            } catch (SQLException e) {
                String message = "Cannot create connection.";
                LOGGER.fatal(message);
                throw new FatalPoolException(message);
            }
        }
        LOGGER.info("Connection pool was created successfully!");
    }

    /**
     * Returns connection from pool if available.
     *
     * @return available connection from connection pool.
     * @throws PoolException if impossible to take connection from the pool.
     */
    public Connection takeConnection() throws PoolException {
        PooledConnection connection;
        try {
            connection = availableConnections.poll(poolTimeout,
                                                   TimeUnit.MILLISECONDS);
            usedConnections.put(Objects.requireNonNull(connection));
            String message = String.format(
                    "Connection was taken from pool. Current pool"
                    + " size: %d used connections; %d available connections.",
                    usedConnections.size(),
                    availableConnections.size());
            LOGGER.debug(message);
        } catch (InterruptedException e) {
            String message = "It is impossible to take connection from the"
                             + " pool.";
            LOGGER.warn(message, e);
            Thread.currentThread().interrupt();
            throw new PoolException(message, e);
        }
        return connection;
    }

    /**
     * Releases used connection and returns it to the pool.
     *
     * @param connection used connection.
     * @throws PoolException if impossible to release connection to the pool.
     */
     void releaseConnection(final Connection connection) throws
            PoolException {
        if (connection instanceof PooledConnection) {
            try {
                if (usedConnections.remove(connection)) {
                    availableConnections.put((PooledConnection) connection);
                    connection.setAutoCommit(true);
                    String message = String.format(
                            "Connection was returned into pool. Current pool"
                            + " size: %d used connections; %d available"
                            + " connections.",
                            usedConnections.size(),
                            availableConnections.size());
                    LOGGER.debug(message);
                }
            } catch (InterruptedException e) {
                String message = "It is impossible to return database "
                                 + "connection into pool";
                LOGGER.warn(message, e);
                Thread.currentThread().interrupt();
                throw new PoolException(message, e);
            } catch (SQLException e) {
                String message = "It is impossible to unable to "
                                 + "auto-commit mode";
                LOGGER.error(message, e);
                throw new PoolException(message, e);
            }
        }
    }

    /**
     * Closes all connections and clear the pool.
     */
    public void closePool() {
        for (int i = 0; i < availableConnections.size(); i++) {
            try {
                PooledConnection connection = availableConnections.take();
                connection.realClose();
            } catch (InterruptedException | SQLException e) {
                String message = "It is impossible to close the "
                                 + "connection pool";
                LOGGER.error(message, e);
                Thread.currentThread().interrupt();
            }
        }
        availableConnections.clear();
    }
}

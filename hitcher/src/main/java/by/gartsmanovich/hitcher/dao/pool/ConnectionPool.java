package by.gartsmanovich.hitcher.dao.pool;

import by.gartsmanovich.hitcher.dao.pool.exception.PoolException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection pool class for web application.
 *
 * @author Dmitry Gartsmanovich
 */
public final class ConnectionPool {

    /**
     * Maximum size of available connections.
     */
    private static final int POOL_SIZE = 10;

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
    private BlockingDeque<WrapperConnection> availableConnections;

    /**
     * Deque for used connections.
     */
    private BlockingDeque<WrapperConnection> usedConnections;

    /**
     * Instantiate connection pool.
     *
     * @throws PoolException if there was created an incorrect connections
     *                       number.
     */
    private ConnectionPool() throws PoolException {
        init();
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String jdbcUrl = "jdbc:mysql://localhost/hitcher_db";
            String login = "hitcher_user1";
            String pass = "password";
            for (int i = 0; i < POOL_SIZE; i++) {

                Connection connection = DriverManager.getConnection(jdbcUrl,
                                                                    login,
                                                                    pass);
                WrapperConnection wrapperConnection = new WrapperConnection(
                        connection);
                availableConnections.offer(wrapperConnection);
            }
            if (availableConnections.size() != POOL_SIZE) {
                throw new PoolException("Incorrect number of connections"
                                        + "during creation!");
            }
        } catch (SQLException e) {
            throw new PoolException(e.getMessage(), e);
        }
    }

    /**
     * Creates (if not exists) and returns threadsafe connection pool instance.
     *
     * @return threadsafe connection pool instance.
     * @throws PoolException if there was created an incorrect connections
     *                       number.
     */
    public static ConnectionPool getInstance() throws PoolException {
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
     *
     */
    private void init() {
        availableConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        usedConnections = new LinkedBlockingDeque<>(POOL_SIZE);
    }

    /**
     * Returns connection from pool if available.
     *
     * @return available connection from connection pool.
     */
    public Connection takeConnection() {
        WrapperConnection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Releases used connection and returns it to the pool.
     *
     * @param connection used connection.
     */
    public void releaseConnection(final Connection connection) {
        if (connection instanceof WrapperConnection) {
            try {
                if (usedConnections.remove(connection)) {
                    availableConnections.put((WrapperConnection) connection);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Closes all connections in the pool.
     */
    public void closePool() {
        for (int i = 0; i < availableConnections.size(); i++) {
            try {
                WrapperConnection connection = availableConnections.take();
                connection.realClose();
            } catch (InterruptedException eValue) {
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

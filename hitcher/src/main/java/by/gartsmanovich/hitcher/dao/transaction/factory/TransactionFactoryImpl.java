package by.gartsmanovich.hitcher.dao.transaction.factory;

import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.pool.ConnectionPool;
import by.gartsmanovich.hitcher.dao.pool.exception.PoolException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.dao.transaction.TransactionImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The transaction factory realisation.
 *
 * @author Dmitry Gartsmanovich
 */
public class TransactionFactoryImpl implements TransactionFactory {

    /**
     * The logger for TransactionImpl class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            TransactionFactoryImpl.class);

    /**
     * Represents connection to the database.
     */
    private Connection connection;

    /**
     * Constructs transaction factory realisation instance. Gets the connection
     * from pool and disables auto-commit mode for this connection.
     *
     * @throws DaoException if it is impossible to turn off auto-committing
     *                      for database connection."
     */
    public TransactionFactoryImpl() throws DaoException {
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            String message =
                    "It is impossible to turn off auto-committing for database"
                    + " connection";
            LOGGER.error(message, e);
            throw new DaoException(message, e);
        } catch (PoolException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Creates transaction instance.
     *
     * @return the transaction instance.
     */
    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    /**
     * Implicitly returns connection to the connection pool.
     *
     * @throws DaoException if it is not possible to release connection.
     */
    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            String message = "It is impossible to release connection.";
            LOGGER.error(message, e);
            throw new DaoException(message, e);
        }
    }
}

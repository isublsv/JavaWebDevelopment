package by.gartsmanovich.hitcher.dao.transaction;

import by.gartsmanovich.hitcher.dao.ReviewDao;
import by.gartsmanovich.hitcher.dao.TripDao;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.impl.MysqlReviewDao;
import by.gartsmanovich.hitcher.dao.impl.MysqlTripDao;
import by.gartsmanovich.hitcher.dao.impl.MysqlUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transaction interface realisation.
 *
 * @author Dmitry Gartsmanovich
 */
public class TransactionImpl implements Transaction {

    /**
     * The logger for TransactionImpl class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            TransactionImpl.class);

    /**
     * Represents connection to the database.
     */
    private Connection connection;

    /**
     * Constructs an Transaction instance with provided connection.
     *
     * @param connectionValue the provided connection.
     */
    public TransactionImpl(final Connection connectionValue) {
        this.connection = connectionValue;
    }

    /**
     * Returns a new instance of user DAO Implementation for the concrete
     * database.
     *
     * @return the concrete user DAO implementation.
     */
    @Override
    public UserDao getUserDao() {
        return new MysqlUserDao(connection);
    }

    /**
     * Returns a new instance of trip DAO Implementation for the concrete
     * database.
     *
     * @return the concrete trip DAO implementation.
     */
    @Override
    public TripDao getTripDao() {
        return new MysqlTripDao(connection);
    }

    /**
     * Returns a new instance of review DAO Implementation for the concrete
     * database.
     *
     * @return the concrete review DAO implementation.
     */
    @Override
    public ReviewDao getReviewDao() {
        return new MysqlReviewDao(connection);
    }

    /**
     * Makes all changes made since the previous commit/rollback permanent and
     * releases any database locks currently held by this Connection object.
     *
     * @throws DaoException if it is impossible to commit transaction.
     * @see Connection#rollback() for more information.
     */
    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            String message = "It is impossible to commit transaction.";
            LOGGER.error(message, e);
            throw new DaoException(message, e);
        }
    }

    /**
     * Undoes all changes made in the current transaction and releases any
     * database locks currently held by this Connection object.
     *
     * @throws DaoException if it is impossible to rollback transaction.
     * @see Connection#rollback() for more information.
     */
    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            String message = "It is impossible to rollback transaction.";
            LOGGER.error(message, e);
            throw new DaoException(message, e);
        }
    }
}

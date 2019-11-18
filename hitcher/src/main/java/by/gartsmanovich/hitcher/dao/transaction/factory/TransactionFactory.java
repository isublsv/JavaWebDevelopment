package by.gartsmanovich.hitcher.dao.transaction.factory;

import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;

/**
 * The factory provides method for transaction instance creation.
 *
 * @author Dmitry Gartsmanovich
 */
public interface TransactionFactory {

    /**
     * Creates transaction instance.
     *
     * @return the transaction instance.
     */
    Transaction createTransaction();

    /**
     * Closes current connection.
     *
     * @throws DaoException if it is not possible to close connection.
     */
    void close() throws DaoException;
}

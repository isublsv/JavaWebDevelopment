package by.gartsmanovich.hitcher.dao.transaction;

import by.gartsmanovich.hitcher.dao.ReviewDao;
import by.gartsmanovich.hitcher.dao.TripDao;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

/**
 * The interface used to provided the low coupling between Service Application
 * layer and Connection pool.
 *
 * @author Dmitry Gartsmanovich
 */
public interface Transaction {

    /**
     * Returns a new instance of user DAO Implementation for the concrete
     * database.
     *
     * @return the concrete user DAO implementation.
     */
    UserDao getUserDao();

    /**
     * Returns a new instance of trip DAO Implementation for the concrete
     * database.
     *
     * @return the concrete trip DAO implementation.
     */
    TripDao getTripDao();

    /**
     * Returns a new instance of review DAO Implementation for the concrete
     * database.
     *
     * @return the concrete review DAO implementation.
     */
    ReviewDao getReviewDao();

    /**
     * Makes all changes made since the previous commit/rollback permanent and
     * releases any database locks currently held by this Connection object.
     *
     * @throws DaoException if something went wrong during execution
     */
    void commit() throws DaoException;

    /**
     * Undoes all changes made in the current transaction and releases any
     * database locks currently held by this Connection object.
     *
     * @throws DaoException if something went wrong during execution
     */
    void rollback() throws DaoException;
}

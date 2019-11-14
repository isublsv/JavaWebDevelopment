package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.Entity;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

/**
 * DAO interface describes the common structure for interfaces for different
 * types of entities.
 *
 * @param <T> the type of entities the DAO interface used for.
 * @author Dmitry Gartsmanovich
 */
public interface AbstractDao<T extends Entity> {

    /**
     * Saves the provided entity in the data source.
     *
     * @param entity the provided entity.
     */
    void create(T entity) throws DaoException, SQLException;

    /**
     * Finds entity in the data source by provided ID if present.
     *
     * @param id the provided ID.
     * @return the entity value if present.
     */
    Optional<T> findById(long id) throws SQLException, DaoException;

    /**
     * Updates the provided entity in the data source.
     *
     * @param entity the provided entity.
     */
    void update(T entity) throws DaoException, SQLException;

    /**
     * Deletes the provided entity from the data source.
     *
     * @param entity the provided entity.
     */
    void delete(T entity) throws DaoException, SQLException;

    /**
     * Finds all entity in the data source.
     *
     * @return the collection of found entities.
     */
    Collection<T> findAll() throws SQLException, DaoException;
}

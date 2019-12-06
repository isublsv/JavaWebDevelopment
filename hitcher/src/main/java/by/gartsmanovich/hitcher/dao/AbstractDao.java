package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.Entity;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.util.Optional;

/**
 * DAO interface describes the common structure for interfaces for different
 * types of entities.
 *
 * @param <T> the type of entities the DAO interface is used for.
 * @author Dmitry Gartsmanovich
 */
public interface AbstractDao<T extends Entity> {

    /**
     * Saves the provided entity in the data source.
     *
     * @param entity the provided entity.
     * @return entity value.
     * @throws DaoException if failed to create entity in the data source.
     */
    default T create(T entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Finds entity in the data source by provided ID if present.
     *
     * @param id the provided ID.
     * @return the entity value if present.
     * @throws DaoException if failed to find entity by ID.
     */
    default Optional<T> findById(long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Updates the provided entity in the data source.
     *
     * @param entity the provided entity.
     * @throws DaoException if failed to update entity in the data source.
     */
    default void update(T entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Deletes the provided entity by ID from the data source.
     *
     * @param id the provided entity ID.
     * @throws DaoException if failed to delete entity by ID.
     */
    default void delete(long id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}

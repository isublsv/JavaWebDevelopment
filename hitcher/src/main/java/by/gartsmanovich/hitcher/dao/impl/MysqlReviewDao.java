package by.gartsmanovich.hitcher.dao.impl;

import by.gartsmanovich.hitcher.bean.Review;
import by.gartsmanovich.hitcher.dao.ReviewDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

/**
 * @author Dmitry Gartsmanovich
 */
public class MysqlReviewDao implements ReviewDao {

    /**
     * Connection from a pool to MySQL database.
     */
    private Connection connection;

    /**
     * Constructs an instance with specific connection.
     *
     * @param connectionValue the provided connection.
     */
    public MysqlReviewDao(final Connection connectionValue) {
        connection = connectionValue;
    }

    /**
     * Saves the provided entity in the data source.
     *
     * @param entity the provided entity.
     * @throws DaoException if failed to create entity in the data source.
     */
    @Override
    public void create(final Review entity) throws DaoException {

    }

    /**
     * Finds entity in the data source by provided ID if present.
     *
     * @param id the provided ID.
     * @return the entity value if present.
     * @throws DaoException if failed to find entity by ID.
     */
    @Override
    public Optional<Review> findById(final long id) throws DaoException {
        return Optional.empty();
    }

    /**
     * Updates the provided entity in the data source.
     *
     * @param entity the provided entity.
     * @throws DaoException if failed to update entity in the data source.
     */
    @Override
    public void update(final Review entity) throws DaoException {

    }

    /**
     * Deletes the provided entity by ID from the data source.
     *
     * @param id the provided entity ID.
     * @throws DaoException if failed to delete entity by ID.
     */
    @Override
    public void delete(final long id) throws DaoException {

    }
}

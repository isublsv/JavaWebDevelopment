package by.gartsmanovich.hitcher.dao.impl;

import by.gartsmanovich.hitcher.bean.Review;
import by.gartsmanovich.hitcher.dao.ReviewDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Dmitry Gartsmanovich
 */
public class MysqlReviewDao implements ReviewDao {

    /**
     * The logger for MysqlReviewDao class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            MysqlReviewDao.class);

    /**
     * Query to add a new review to the database.
     */
    private static final String INSERT_REVIEW =
            "INSERT INTO hitcher_db.reviews (about_id, "
            + "who_id, text, rating) VALUES ( ?, ?, ?, ?)";

    /**
     * Query to find a review by ID of the subject in the database.
     */
    private static final String FIND_BY_ID =
            "SELECT r.about_id, r.who_id, r.text, r.rating "
            + "FROM reviews AS r WHERE r.id=?;";

    /**
     * Query to update data of selected review in the database.
     */
    private static final String UPDATE_REVIEW =
            "UPDATE hitcher_db.reviews AS r SET r.about_id=?, r.who_id=?,"
            + " r.text=?, r.rating=? WHERE r.id=?;";

    /**
     * Query to delete selected user from the database.
     */
    private static final String DELETE_REVIEW =
            "DELETE FROM hitcher_db.reviews WHERE reviews.id = ?;";

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
     * Saves the provided review entity in the database.
     *
     * @param entity the provided review entity.
     * @throws DaoException if failed to create review entity in the database.
     */
    @Override
    public void create(final Review entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                INSERT_REVIEW)) {

            int counter = 1;

            statement.setLong(counter++, entity.getAboutId());
            statement.setLong(counter++, entity.getWhoId());
            statement.setString(counter++, entity.getText());
            statement.setInt(counter, entity.getRating());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setId(keys.getInt(1));
                } else {
                    String message = "Failed to get auto-incremented ID value"
                                     + " during INSERT REVIEW operation.";
                    LOGGER.error(message);
                    throw new DaoException(message);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to create review!", e);
        }
    }

    /**
     * Finds review entity in the database by provided ID if present.
     *
     * @param id the provided ID.
     * @return the review entity if present.
     * @throws DaoException if failed to find review entity by ID.
     */
    @Override
    public Optional<Review> findById(final long id) throws DaoException {
        Review review = null;
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_BY_ID)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    review = getReview(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find review by ID!", e);
        }
        return Optional.ofNullable(review);
    }


    /**
     * Updates the provided review entity in the database.
     *
     * @param entity the provided review entity.
     * @throws DaoException if failed to update entity in the database.
     */
    @Override
    public void update(final Review entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                UPDATE_REVIEW)) {
            int counter = 1;

            statement.setLong(counter++, entity.getAboutId());
            statement.setLong(counter++, entity.getWhoId());
            statement.setString(counter++, entity.getText());
            statement.setInt(counter++, entity.getRating());

            statement.setLong(counter, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update review!", e);
        }
    }

    /**
     * Deletes the provided review entity by ID from the database.
     *
     * @param id the provided review entity ID.
     * @throws DaoException if failed to delete entity by ID.
     */
    @Override
    public void delete(final long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                DELETE_REVIEW)) {

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to delete review!", e);
        }
    }

    /**
     * Creates review entity from provided result set data.
     *
     * @param resultSet the provided result set data from database.
     * @return the result review entity.
     * @throws SQLException if an error occurs during operation execution.
     */
    private Review getReview(final ResultSet resultSet) throws SQLException {
        Review review = new Review();
        int counter = 1;

        review.setId(resultSet.getInt(counter++));
        review.setAboutId(resultSet.getInt(counter++));
        review.setWhoId(resultSet.getInt(counter++));
        review.setText(resultSet.getString(counter++));
        review.setRating(resultSet.getInt(counter));

        return review;
    }
}

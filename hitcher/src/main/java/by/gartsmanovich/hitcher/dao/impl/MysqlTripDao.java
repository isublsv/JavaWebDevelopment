package by.gartsmanovich.hitcher.dao.impl;

import by.gartsmanovich.hitcher.bean.City;
import by.gartsmanovich.hitcher.bean.Trip;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.AbstractDao;
import by.gartsmanovich.hitcher.dao.TripDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * The Trip DAO implementation is used to execute queries to the MySQL database.
 *
 * @author Dmitry Gartsmanovich
 */
public class MysqlTripDao implements AbstractDao<Trip>, TripDao {

    /**
     * The logger for MysqlTripDao class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            MysqlTripDao.class);

    /**
     * Query to add a new trip to the database.
     */
    private static final String INSERT_TRIP =
            "INSERT INTO hitcher_db.trips (driver_id, `from_city_id`,"
            + " `to_city_id`, departure_datetime, arrival_datetime)"
            + " VALUES (?, ?, ?, ?, ?);";

    /**
     * Common part of the find trip query.
     */
    private static final String FIND_TRIP =
            "SELECT t.id, t.driver_id, t.from_city_id, t.to_city_id,"
            + " t.departure_datetime, t.arrival_datetime, top.free_seats,"
            + " top.price, top.smoking, top.pets FROM trips AS t"
            + " LEFT JOIN trip_options AS top ON t.id = top.trip_id";

    /**
     * Query to find a trip by ID value in the database.
     */
    private static final String FIND_BY_ID = FIND_TRIP + " WHERE t.id=?;";

    /**
     * Query to find a trips by values in the database.
     */
    private static final String FIND_BY_PARAMS =
            FIND_TRIP + " WHERE t.from_city_id=? AND t.to_city_id=?"
            + " AND t.departure_datetime > ?;";

    /**
     * Query to find a trips by user ID in the database.
     */
    private static final String FIND_TRIP_BY_USER_ID =
            FIND_TRIP + " WHERE t.driver_id=?;";

    /**
     * Query to update data of the trip in the database.
     */
    private static final String UPDATE_TRIP =
            "UPDATE hitcher_db.trips AS t SET t.`from_city_id`=?, "
            + "t.`to_city_id`=?, t.departure_datetime=?, t.arrival_datetime=?"
            + " WHERE t.id=?;";

    /**
     * Query to delete selected trip from the database.
     */
    private static final String DELETE_TRIP = "DELETE FROM hitcher_db.trips "
                                              + "WHERE trips.id = ?;";

    /**
     * Connection from a pool to MySQL database.
     */
    private Connection connection;

    /**
     * Constructs an instance with specific connection.
     *
     * @param connectionValue the provided connection.
     */
    public MysqlTripDao(final Connection connectionValue) {
        connection = connectionValue;
    }

    /**
     * Saves the provided trip entity to the database.
     *
     * @param entity the provided trip entity.
     * @throws DaoException if failed to create trip in the database or
     * to get auto-incremented key.
     */
    @Override
    public void create(final Trip entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                INSERT_TRIP, RETURN_GENERATED_KEYS)) {
            int counter = 1;

            statement.setLong(counter++, entity.getDriver().getId());
            /*statement.setString(counter++, entity.getFrom());
            statement.setString(counter++, entity.getTo());*/
            Date departureDatetime = Date.valueOf(
                    entity.getDepartureDatetime());
            statement.setDate(counter++, departureDatetime);
            Date arrivalDatetime = Date.valueOf(entity.getArrivalDatetime());
            statement.setDate(counter, arrivalDatetime);

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setId(keys.getInt(1));
                } else {
                    String message = "Failed to get auto-incremented ID value"
                                     + " during INSERT TRIP operation.";
                    LOGGER.error(message);
                    throw new DaoException(message);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to create trip!", e);
        }
    }

    /**
     * Finds trip entity in the database by provided ID if present.
     *
     * @param id the provided ID.
     * @return the trip value if present.
     * @throws DaoException if failed to find trip by ID in the database.
     */
    @Override
    public Optional<Trip> findById(final long id) throws DaoException {
        Trip trip = null;
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_BY_ID)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    trip = getTrip(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find trip by ID!", e);
        }
        return Optional.ofNullable(trip);
    }

    /**
     * Updates the provided trip entity in the database.
     *
     * @param entity the provided trip entity.
     * @throws DaoException if failed to update trip entity in the database.
     */
    @Override
    public void update(final Trip entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                UPDATE_TRIP)) {
            int counter = 1;

            /*statement.setString(counter++, entity.getFrom());
            statement.setString(counter++, entity.getTo());*/
            statement.setDate(counter++,
                              Date.valueOf(entity.getDepartureDatetime()));
            statement.setDate(counter++,
                              Date.valueOf(entity.getArrivalDatetime()));

            statement.setLong(counter, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update trip!", e);
        }
    }

    /**
     * Deletes the provided trip entity by ID from the database.
     *
     * @param id the provided trip ID.
     * @throws DaoException if failed to delete trip entity from the database.
     */
    @Override
    public void delete(final long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                DELETE_TRIP)) {

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to delete trip!", e);
        }
    }

    /**
     * Returns trip list from city {@param fromId} to city {@param toId}
     * at the day of {@param departureDate} in the database.
     *
     * @param fromId        the provided city value ID from which the trip
     *                      will begin.
     * @param toId          the provided city value ID of trip destination.
     * @param departureDate the departure day.
     * @return the trip list.
     * @throws DaoException if failed to find trip list in the database.
     */
    @Override
    public List<Trip> findByValues(final int fromId, final int toId,
            final LocalDate departureDate) throws DaoException {
        List<Trip> trips = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_BY_PARAMS)) {
            int counter = 1;

            statement.setLong(counter++, fromId);
            statement.setLong(counter++, toId);
            statement.setDate(counter, Date.valueOf(departureDate));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Trip trip = getTrip(resultSet);
                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find trips by Values!", e);
        }
        return trips;
    }

    /**
     * Returns user trip list from the database.
     *
     * @param id the provided user ID.
     * @return the trip list.
     * @throws DaoException if failed to find trip list by ID from the
     *                      database.
     */
    @Override
    public List<Trip> findByUserId(final long id) throws DaoException {
        List<Trip> trips = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_TRIP_BY_USER_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Trip trip = getTrip(resultSet);
                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find trips by id!", e);
        }
        return trips;
    }

    /**
     * Creates trip entity from provided result set data.
     *
     * @param resultSet the provided result set data from database.
     * @return the result trip entity.
     * @throws SQLException if an error occurs during operation execution.
     */
    private Trip getTrip(final ResultSet resultSet) throws SQLException {
        Trip trip = new Trip();
        int counter = 1;

        trip.setId(resultSet.getLong(counter++));

        User driver = new User();
        driver.setId(resultSet.getLong(counter++));
        trip.setDriver(driver);

        City from = new City();
        from.setId(resultSet.getLong(counter++));
        trip.setFrom(from);

        City to = new City();
        to.setId(resultSet.getLong(counter++));
        trip.setTo(to);

        trip.setDepartureDatetime(resultSet.getDate(counter++).toLocalDate());
        trip.setArrivalDatetime(resultSet.getDate(counter++).toLocalDate());
        trip.setFreeSeats(resultSet.getInt(counter++));
        trip.setPrice(resultSet.getDouble(counter++));
        trip.setSmokingAllowed(resultSet.getBoolean(counter++));
        trip.setPetsAllowed(resultSet.getBoolean(counter));

        return trip;
    }
}

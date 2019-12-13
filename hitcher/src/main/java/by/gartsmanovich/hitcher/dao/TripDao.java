package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.Trip;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface describes the common structure for trip type DAO.
 *
 * @author Dmitry Gartsmanovich
 */
public interface TripDao extends AbstractDao<Trip> {

    /**
     * Returns trip list from city {@param fromId} to city {@param toId}
     * at the day of {@param departureDate} in the data source.
     *
     * @param fromId        the provided city value ID from which the trip
     *                      will begin.
     * @param toId          the provided city value ID of trip destination.
     * @param departureDate the departure day.
     * @return the trip list.
     * @throws DaoException if failed to find trip list in the data source.
     */
    List<Trip> findByValues(int fromId, int toId,
            LocalDate departureDate) throws DaoException;

    /**
     * Returns user trip list from the data source.
     *
     * @param id the provided user ID.
     * @return the trip list.
     * @throws DaoException if failed to find trip list by ID from the
     *                      data source.
     */
    List<Trip> findByUserId(long id) throws DaoException;

    /**
     * Insert additional info of the provided trip in the data source.
     *
     * @param trip the provided entity.
     * @throws DaoException if failed to insert additional info of the provided
     *                      trip in the data source.
     */
    void addTripInfo(Trip trip) throws DaoException;

    /**
     * Deletes trip additional information by id from the data source.
     *
     * @param id the provided trip ID.
     * @throws DaoException if failed to delete additional info by ID
     *                      from the data source.
     */
    void deleteTripInfo(long id) throws DaoException;

    /**
     * Register user to selected trip.
     *
     * @param userId the provided user ID.
     * @param tripId the provided trip ID.
     * @throws DaoException if failed to register user to selected trip.
     */
    void addPassenger(long userId, long tripId) throws DaoException;

    /**
     * Finds all passengers of the trip in the data source.
     *
     * @param id the provided tip ID.
     * @return the list of passengers.
     * @throws DaoException if failed to finds all passengers of selected trip
     *                      in the data source.
     */
    List<User> findPassengers(long id) throws DaoException;

    /**
     * Unregister user from selected trip.
     *
     * @param userId the provided user ID.
     * @param tripId the provided trip ID.
     * @throws DaoException if failed to unregister user from selected trip.
     */
    void deletePassenger(long userId, long tripId) throws DaoException;
}

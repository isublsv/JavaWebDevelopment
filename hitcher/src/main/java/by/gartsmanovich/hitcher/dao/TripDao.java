package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.Trip;
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
}

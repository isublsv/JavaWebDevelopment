package by.gartsmanovich.hitcher.service;

import by.gartsmanovich.hitcher.bean.Trip;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The interface is used to determine the common structure of Trip Service Layer
 * class.
 *
 * @author Dmitry Gartsmanovich
 */
public interface TripService {

    /**
     * Returns trip list from city {@param cityFrom} to city {@param cityTo}
     * at the day of {@param departure}.
     *
     * @param cityFrom the provided city value from which the trip will begin.
     * @param cityTo the provided city value of trip destination.
     * @param departure the departure day.
     * @return the trip list.
     * @throws ServiceException if failed to find trip list in the data source.
     */
    List<Trip> findTripsByValues(String cityFrom, String cityTo,
            String departure) throws ServiceException;

    /**
     * Returns user trip list from the data source.
     *
     * @param id the provided user ID.
     * @return the trip list.
     * @throws ServiceException if failed to find trip list by ID from the data
     *                          source.
     */
    List<Trip> findTripsByUserId(long id) throws ServiceException;

    /**
     * Saves a new user trip in the data source.
     *
     * @param id  the provided user ID.
     * @param map the parameters map.
     * @throws ServiceException if failed to save a new user trip in the data
     *                          source.
     */
    void save(long id, Map<String, String[]> map) throws ServiceException;

    /**
     * Updates a trip information in the data source.
     *
     * @param map the parameters map.
     * @throws ServiceException if failed to update a trip information in the
     *                          data source.
     */
    void update(Map<String, String[]> map) throws ServiceException;

    /**
     * Finds trip and user IDs in the data source.
     *
     * @param userId the provided user ID
     * @param tripId the provided trip ID.
     * @return the trip entity.
     * @throws ServiceException if failed to find trip and user IDs in the data
     *                          source.
     */
    Trip findTripById(long userId, String tripId) throws ServiceException;

    /**
     * Deletes trip by ID from the data source.
     *
     * @param tripId the provided trip ID.
     * @throws ServiceException if failed to delete trip by ID from the data
     *                          source.
     */
    void deleteTripById(String tripId) throws ServiceException;

    /**
     * Register user to selected trip.
     *
     * @param userId the provided user ID.
     * @param tripId the provided trip ID.
     * @throws ServiceException if failed to register user to trip by ID.
     */
    void addPassenger(long userId, String tripId) throws ServiceException;

    /**
     * Unregister user from selected trip.
     *
     * @param userId the provided user ID.
     * @param tripId the provided trip ID.
     * @throws ServiceException if failed to unregister user from trip by ID.
     */
    void deletePassenger(long userId, String tripId) throws ServiceException;
}

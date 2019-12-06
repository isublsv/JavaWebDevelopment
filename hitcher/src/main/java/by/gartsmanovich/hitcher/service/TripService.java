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
     * @throws ServiceException if failed to find trip list.
     */
    List<Trip> findTripsByValues(String cityFrom, String cityTo,
            String departure) throws ServiceException;

    /**
     * Returns user trip list.
     *
     * @param id the provided user ID.
     * @return the trip list.
     * @throws ServiceException if failed to find trip list by ID.
     */
    List<Trip> findTripsById(long id) throws ServiceException;

    /**
     * Saves a new user trip.
     *
     * @param id the provided user ID.
     * @param map the parameters map.
     * @throws ServiceException if failed to save a new user trip.
     */
    void save(long id, Map<String, String[]> map) throws ServiceException;
}

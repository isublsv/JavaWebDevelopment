package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.City;
import by.gartsmanovich.hitcher.bean.Destination;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface describes the common structure for destination type DAO.
 *
 * @author Dmitry Gartsmanovich
 */
public interface DestinationDao extends AbstractDao<Destination> {

    /**
     * Finds all possible destination in the data source.
     *
     * @return the list of destinations.
     * @throws DaoException if failed to find all destination entities in the
     *                      data source.
     */
    List<Destination> findAllDestinations() throws DaoException;

    /**
     * Finds all cities by destination ID in the data source.
     *
     * @param id the provided ID.
     * @return the list of destinations.
     * @throws DaoException if failed to find all city entities by destination
     *                      ID in the data source.
     */
    List<City> findAllCitiesById(long id) throws DaoException;

    /**
     * Finds city by ID in the data source if present.
     *
     * @param id the provided city ID.
     * @return the city entity if present.
     * @throws DaoException if failed to find city entity by ID in the
     *                      data source.
     */
    Optional<City> findByCityId(long id) throws DaoException;
}

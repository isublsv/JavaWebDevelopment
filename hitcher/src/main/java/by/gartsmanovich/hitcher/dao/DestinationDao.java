package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.Destination;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.util.List;

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
    List<Destination> findAll() throws DaoException;
}

package by.gartsmanovich.hitcher.service;

import by.gartsmanovich.hitcher.bean.Destination;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

import java.util.List;

/**
 * The interface is used to determine the common structure of Destination
 * Service Layer class.
 *
 * @author Dmitry Gartsmanovich
 */
public interface DestinationService {

    /**
     * Finds all possible destination in the data source.
     *
     * @return the list of destinations.
     * @throws ServiceException if failed to find all destination entities in
     *                          the data source.
     */
    List<Destination> findAll() throws ServiceException;
}

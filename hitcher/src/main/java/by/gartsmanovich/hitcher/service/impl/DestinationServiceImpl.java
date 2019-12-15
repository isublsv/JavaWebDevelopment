package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.City;
import by.gartsmanovich.hitcher.bean.Destination;
import by.gartsmanovich.hitcher.dao.DestinationDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.DestinationService;
import by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

import java.util.List;

/**
 * The realisation of Destination Service interface is used to describe of
 * application functionality for the destination entity.
 *
 * @author Dmitry Gartsmanovich
 */
public class DestinationServiceImpl implements DestinationService {

    /**
     * Represents the transaction entity.
     */
    private Transaction transaction;

    /**
     * Constructs the Destination Service realisation instance with provided
     * transaction value.
     *
     * @param transactionValue the provided transaction.
     */
    public DestinationServiceImpl(final Transaction transactionValue) {
        this.transaction = transactionValue;
    }

    /**
     * Finds all possible destination in the data source.
     *
     * @return the list of destinations.
     * @throws ServiceException if failed to find all destination entities in
     *                          the data source.
     */
    @Override
    public List<Destination> findAll() throws ServiceException {
        DestinationDao dao = transaction.getDestinationDao();
        try {
            List<Destination> destinations = dao.findAllDestinations();
            for (Destination destination : destinations) {
                List<City> cities = dao.findAllCitiesById(destination.getId());
                destination.setCities(cities);
            }
            transaction.commit();
            return destinations;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, ServiceErrorCodes.SQL_ERROR);
        }
    }
}

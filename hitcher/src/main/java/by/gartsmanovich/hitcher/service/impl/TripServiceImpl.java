package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.Trip;
import by.gartsmanovich.hitcher.dao.TripDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.TripService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.validator.ServiceValidator;

import java.time.LocalDate;
import java.util.List;

import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_CITY_VALUES;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_DATE_FORMAT;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.SQL_ERROR;

/**
 * The realisation of Trip Service interface is used to describe of application
 * functionality for the trip entity.
 *
 * @author Dmitry Gartsmanovich
 */
public class TripServiceImpl implements TripService {

    /**
     * Represents the transaction entity.
     */
    private Transaction transaction;

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private ServiceValidator validator;

    /**
     * Constructs the Trip Service realisation instance with provided
     * transaction value.
     *
     * @param transactionValue the provided transaction.
     */
    public TripServiceImpl(final Transaction transactionValue) {
        this.transaction = transactionValue;
        this.validator = new ServiceValidator();
    }

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
    @Override
    public List<Trip> findTripsByValues(final String cityFrom,
            final String cityTo, final String departure)
            throws ServiceException {

        if (!validator.isValidNumbers(cityFrom, cityTo)) {
            throw new ServiceException(INVALID_CITY_VALUES);
        }
        if (!validator.isValidDate(departure)) {
            throw new ServiceException(INVALID_DATE_FORMAT);
        }

        TripDao tripDao = transaction.getTripDao();
        try {
            int cityFromId = Integer.parseInt(cityFrom);
            int cityToId = Integer.parseInt(cityTo);
            LocalDate departureDate = LocalDate.parse(departure);
            return tripDao.findByValues(cityFromId, cityToId, departureDate);
        } catch (DaoException e) {
            throw new ServiceException(SQL_ERROR);
        }
    }
}

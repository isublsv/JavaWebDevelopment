package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.City;
import by.gartsmanovich.hitcher.bean.Trip;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.DestinationDao;
import by.gartsmanovich.hitcher.dao.TripDao;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.TripService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.validator.ServiceValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_CITY_VALUES;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_DATE_FORMAT;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_PARAMETERS_NUMBER;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_VALUES;
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
        UserDao userDao = transaction.getUserDao();
        DestinationDao destinationDao = transaction.getDestinationDao();
        try {
            int cityFromId = Integer.parseInt(cityFrom);
            int cityToId = Integer.parseInt(cityTo);
            LocalDate departureDate = LocalDate.parse(departure);
            List<Trip> trips = tripDao.findByValues(cityFromId, cityToId,
                                                       departureDate);

            for (Trip trip : trips) {
                Optional<User> driver = userDao.findById(
                        trip.getDriver().getId());
                driver.ifPresent(trip::setDriver);

                Optional<City> cityFromEntity = destinationDao
                        .findCityById(cityFromId);
                Optional<City> cityToEntity = destinationDao
                        .findCityById(cityToId);

                cityFromEntity.ifPresent(trip::setFrom);
                cityToEntity.ifPresent(trip::setTo);
            }
            return trips;
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Returns user trip list.
     *
     * @param id the provided user ID.
     * @return the trip list.
     * @throws ServiceException if failed to find trip list by ID.
     */
    @Override
    public List<Trip> findTripsById(final long id) throws ServiceException {
        TripDao tripDao = transaction.getTripDao();
        DestinationDao destinationDao = transaction.getDestinationDao();
        try {
            List<Trip> trips = tripDao.findByUserId(id);
            for (Trip trip : trips) {
                Optional<City> from = destinationDao.findCityById(
                        trip.getFrom().getId());
                Optional<City> to = destinationDao.findCityById(
                        trip.getTo().getId());

                from.ifPresent(trip::setFrom);
                to.ifPresent(trip::setTo);
            }
            return trips;
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Saves a new user trip.
     *
     * @param id  the provided user ID.
     * @param map the parameters map.
     * @throws ServiceException if failed to save a new user trip.
     */
    @Override
    public void save(final long id, final Map<String, String[]> map) throws
            ServiceException {

        Trip trip = buildTrip(id, map);
        TripDao tripDao = transaction.getTripDao();
        try {
            Trip tripWithId = tripDao.create(trip);
            tripDao.addTripInfo(tripWithId);
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Builds and validates trip entity from provided request parameters.
     *
     * @param id  the provided driver ID.
     * @param map the provided parameters map.
     * @return the trip entity.
     * @throws ServiceException if failed to build trip entity.
     */
    private Trip buildTrip(final long id,
            final Map<String, String[]> map) throws ServiceException {

        Trip trip = new Trip();
        User driver = new User(id);
        trip.setDriver(driver);

        for (final Map.Entry<String, String[]> entry : map.entrySet()) {
            final String key = entry.getKey();
            String[] value;
            switch (key) {
                case "from":
                    value = entry.getValue();
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_CITY_VALUES);
                    } else {
                        City city = new City(Long.parseLong(value[0]));
                        trip.setFrom(city);
                    }
                    break;
                case "to":
                    value = entry.getValue();
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_CITY_VALUES);
                    } else {
                        City city = new City(Long.parseLong(value[0]));
                        trip.setTo(city);
                    }
                    break;
                case "departure":
                    value = entry.getValue();
                    String depDate = value[0];
                    if (!validator.isValidDate(depDate)) {
                        throw new ServiceException(INVALID_DATE_FORMAT);
                    } else {
                        trip.setDepartureDatetime(LocalDate.parse(depDate));
                    }
                    break;
                case "arrival":
                    value = entry.getValue();
                    String arrDate = value[0];
                    if (!validator.isValidDate(arrDate)) {
                        throw new ServiceException(INVALID_DATE_FORMAT);
                    } else {
                        trip.setArrivalDatetime(LocalDate.parse(arrDate));
                    }
                    break;
                case "seats":
                    value = entry.getValue();
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_VALUES);
                    } else {
                        trip.setFreeSeats(Integer.parseInt(value[0]));
                    }
                    break;
                case "price":
                    value = entry.getValue();
                    if (!validator.isValidDecimal(value)) {
                        throw new ServiceException(INVALID_VALUES);
                    } else {
                        trip.setPrice(Double.parseDouble(value[0]));
                    }
                    break;
                case "smoking":
                    value = entry.getValue();
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_VALUES);
                    } else {
                        trip.setSmokingAllowed(Boolean.parseBoolean(value[0]));
                    }
                    break;
                case "pets":
                    value = entry.getValue();
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_VALUES);
                    } else {
                        trip.setPetsAllowed(Boolean.parseBoolean(value[0]));
                    }
                    break;
                default:
                    throw new ServiceException(INVALID_PARAMETERS_NUMBER);
            }
        }
        return trip;
    }
}

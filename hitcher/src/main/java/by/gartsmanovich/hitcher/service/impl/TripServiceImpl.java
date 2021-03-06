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

import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.DRIVER_EXIST;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_CITY_VALUES;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_DATE_FORMAT;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_PARAMETER_VALUE;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.PASSENGER_LIST_NOT_EMPTY;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.SQL_ERROR;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.TRIP_NOT_FOUND;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.USER_NOT_FOUND;

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
     * @throws ServiceException if failed to find trip list in the data source.
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

                trip.setPassengers(findPassengers(trip));
            }
            transaction.commit();
            return trips;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Returns user trip list from the data source.
     *
     * @param userId the provided user ID.
     * @return the trip list.
     * @throws ServiceException if failed to find trip list by ID from the data
     *                          source.
     */
    @Override
    public List<Trip> findTripsByUserId(final long userId)
            throws ServiceException {
        TripDao tripDao = transaction.getTripDao();
        UserDao userDao = transaction.getUserDao();
        DestinationDao destinationDao = transaction.getDestinationDao();
        try {
            List<Trip> trips = tripDao.findByUserId(userId);
            Optional<User> optionalUser = userDao.findById(userId);
            if (optionalUser.isPresent()) {
                for (Trip trip : trips) {
                    trip.setDriver(optionalUser.get());
                    Optional<City> from = destinationDao.findCityById(
                            trip.getFrom().getId());
                    Optional<City> to = destinationDao.findCityById(
                            trip.getTo().getId());

                    from.ifPresent(trip::setFrom);
                    to.ifPresent(trip::setTo);

                    trip.setPassengers(findPassengers(trip));
                }
            } else {
                throw new ServiceException(USER_NOT_FOUND);
            }
            transaction.commit();
            return trips;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Saves a new user trip in the data source.
     *
     * @param userId  the provided user ID.
     * @param map the parameters map.
     * @throws ServiceException if failed to save a new user trip in the data
     *                          source.
     */
    @Override
    public void save(final long userId, final Map<String, String[]> map) throws
            ServiceException {

        UserDao userDao = transaction.getUserDao();
        TripDao tripDao = transaction.getTripDao();
        try {
            Optional<User> optionalUser = userDao.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getDriverLicenseNumber() == null) {
                    throw new ServiceException(DRIVER_EXIST);
                }
            }
            Trip trip = buildTrip(map);

            if (!validator.isValidDates(trip.getDepartureDatetime(),
                                        trip.getArrivalDatetime())) {
                throw new ServiceException(INVALID_DATE_FORMAT);
            }

            User driver = new User(userId);
            trip.setDriver(driver);

            Trip tripWithId = tripDao.create(trip);
            tripDao.addTripInfo(tripWithId);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Updates a trip information in the data source.
     *
     * @param map the parameters map.
     * @throws ServiceException if failed to update a trip information in the
     *                          data source.
     */
    @Override
    public void update(final Map<String, String[]> map) throws
            ServiceException {
        Trip trip = buildTrip(map);
        TripDao tripDao = transaction.getTripDao();
        try {
            Optional<Trip> optionalTrip = tripDao.findById(trip.getId());
            if (optionalTrip.isPresent()) {
                Trip tripToUpdate = optionalTrip.get();

                if (!validator.isValidDates(trip.getDepartureDatetime(),
                                            trip.getArrivalDatetime())) {
                    throw new ServiceException(INVALID_DATE_FORMAT);
                }

                tripToUpdate.setDepartureDatetime(trip.getDepartureDatetime());
                tripToUpdate.setArrivalDatetime(trip.getArrivalDatetime());
                tripToUpdate.setFreeSeats(trip.getFreeSeats());
                tripToUpdate.setPrice(trip.getPrice());
                tripToUpdate.setSmokingAllowed(trip.isSmokingAllowed());
                tripToUpdate.setPetsAllowed(trip.isPetsAllowed());

                tripDao.update(tripToUpdate);
                transaction.commit();
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Finds trip by ID in the data source.
     *
     * @param tripId the provided trip ID.
     * @return the trip entity.
     * @throws ServiceException if failed to find trip by ID in the data
     *                          source.
     */
    @Override
    public Trip findTripById(final String tripId) throws ServiceException {

        TripDao tripDao = transaction.getTripDao();
        DestinationDao destinationDao = transaction.getDestinationDao();
        UserDao userDao = transaction.getUserDao();
        try {
            if (!validator.isValidNumbers(tripId)) {
                throw new ServiceException(INVALID_PARAMETER_VALUE);
            }

            Optional<Trip> optionalTrip = tripDao
                    .findById(Long.parseLong(tripId));
            if (optionalTrip.isPresent()) {
                Trip trip = optionalTrip.get();

                Optional<City> from = destinationDao.findCityById(
                        trip.getFrom().getId());
                Optional<City> to = destinationDao.findCityById(
                        trip.getTo().getId());

                from.ifPresent(trip::setFrom);
                to.ifPresent(trip::setTo);

                Optional<User> optionalUser = userDao.findById(trip.getDriver()
                                                                   .getId());

                optionalUser.ifPresent(trip::setDriver);

                trip.setPassengers(findPassengers(trip));

                transaction.commit();
                return trip;
            } else {
                throw new ServiceException(TRIP_NOT_FOUND);
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Deletes trip by ID from the data source.
     *
     * @param tripId the provided trip ID.
     * @throws ServiceException if failed to delete trip by ID from the data
     *                          source.
     */
    @Override
    public void deleteTripById(final String tripId) throws ServiceException {

        if (!validator.isValidNumbers(tripId)) {
            throw new ServiceException(INVALID_PARAMETER_VALUE);
        }

        TripDao tripDao = transaction.getTripDao();
        try {
            long id = Long.parseLong(tripId);

            List<User> passengers = findPassengers(new Trip(id));
            if (!passengers.isEmpty()) {
                throw new ServiceException(PASSENGER_LIST_NOT_EMPTY);
            }

            tripDao.deleteTripInfo(id);
            tripDao.delete(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Register user to selected trip.
     *
     * @param userId the provided user ID.
     * @param tripId the provided trip ID.
     * @throws ServiceException if failed to register user to trip by ID.
     */
    @Override
    public void addPassenger(final long userId, final String tripId) throws
            ServiceException {

        if (!validator.isValidNumbers(tripId)) {
            throw new ServiceException(INVALID_PARAMETER_VALUE);
        }

        TripDao tripDao = transaction.getTripDao();
        try {
            tripDao.addPassenger(userId, Long.parseLong(tripId));
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Unregister user from selected trip.
     *
     * @param userId the provided user ID.
     * @param tripId the provided trip ID.
     * @throws ServiceException if failed to unregister user from trip by ID.
     */
    @Override
    public void deletePassenger(final long userId, final String tripId) throws
            ServiceException {

        if (!validator.isValidNumbers(tripId)) {
            throw new ServiceException(INVALID_PARAMETER_VALUE);
        }

        TripDao tripDao = transaction.getTripDao();
        try {
            tripDao.deletePassenger(userId, Long.parseLong(tripId));
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Finds passengers of selected trip.
     *
     * @param trip the provided trip entity.
     * @return the list of passengers.
     * @throws DaoException if failed to find passengers of selected trip.
     */
    private List<User> findPassengers(final Trip trip) throws DaoException {
        TripDao tripDao = transaction.getTripDao();
        UserDao userDao = transaction.getUserDao();

        List<User> passengers = tripDao.findPassengers(trip.getId());
        for (int i = 0; i < passengers.size(); i++) {
            User user = passengers.get(i);
            Optional<User> optionalUser = userDao.findById(user.getId());

            if (optionalUser.isPresent()) {
                passengers.set(i, optionalUser.get());
            }
        }
        return passengers;
    }

    /**
     * Builds and validates trip entity from provided request parameters.
     *
     * @param map the provided parameters map.
     * @return the trip entity.
     * @throws ServiceException if failed to build trip entity.
     */
    private Trip buildTrip(final Map<String, String[]> map)
            throws ServiceException {

        Trip trip = new Trip();

        for (final Map.Entry<String, String[]> entry : map.entrySet()) {
            final String key = entry.getKey();
            String[] value = entry.getValue();
            switch (key) {
                case "id":
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_PARAMETER_VALUE);
                    } else {
                        trip.setId(Long.parseLong(value[0]));
                    }
                    break;
                case "from":
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_CITY_VALUES);
                    } else {
                        City city = new City(Long.parseLong(value[0]));
                        trip.setFrom(city);
                    }
                    break;
                case "to":
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_CITY_VALUES);
                    } else {
                        City city = new City(Long.parseLong(value[0]));
                        trip.setTo(city);
                    }
                    break;
                case "departure":
                    String depDate = value[0];
                    if (!validator.isValidDate(depDate)) {
                        throw new ServiceException(INVALID_DATE_FORMAT);
                    } else {
                        trip.setDepartureDatetime(LocalDate.parse(depDate));
                    }
                    break;
                case "arrival":
                    String arrDate = value[0];
                    if (!validator.isValidDate(arrDate)) {
                        throw new ServiceException(INVALID_DATE_FORMAT);
                    } else {
                        trip.setArrivalDatetime(LocalDate.parse(arrDate));
                    }
                    break;
                case "seats":
                    if (!validator.isValidNumbers(value)) {
                        throw new ServiceException(INVALID_PARAMETER_VALUE);
                    } else {
                        trip.setFreeSeats(Integer.parseInt(value[0]));
                    }
                    break;
                case "price":
                    if (!validator.isValidDecimal(value)) {
                        throw new ServiceException(INVALID_PARAMETER_VALUE);
                    } else {
                        trip.setPrice(Double.parseDouble(value[0]));
                    }
                    break;
                case "smoking":
                    if (!validator.isValidValues(value)) {
                        throw new ServiceException(INVALID_PARAMETER_VALUE);
                    } else {
                        trip.setSmokingAllowed(Boolean.parseBoolean(value[0]));
                    }
                    break;
                case "pets":
                    if (!validator.isValidValues(value)) {
                        throw new ServiceException(INVALID_PARAMETER_VALUE);
                    } else {
                        trip.setPetsAllowed(Boolean.parseBoolean(value[0]));
                    }
                    break;
                default:
                    throw new ServiceException(INVALID_PARAMETER_VALUE);
            }
        }
        return trip;
    }
}

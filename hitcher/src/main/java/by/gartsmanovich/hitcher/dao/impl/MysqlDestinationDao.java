package by.gartsmanovich.hitcher.dao.impl;

import by.gartsmanovich.hitcher.bean.City;
import by.gartsmanovich.hitcher.bean.Destination;
import by.gartsmanovich.hitcher.dao.DestinationDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The realisation of Destination Service interface is used to describe of
 * application functionality for the destination entity.
 *
 * @author Dmitry Gartsmanovich
 */
public class MysqlDestinationDao implements DestinationDao {

    /**
     * Query to find all possible destinations in the database.
     */
    private static final String FIND_ALL_DESTINATIONS =
            "SELECT c.id, c.name FROM country AS c ";

    /**
     * Common part of query to find city in the database.
     */
    private static final String COMMON_QUERY_PART =
            "SELECT id, name, country_id FROM city";

    /**
     * Query to find city in the database by ID.
     */
    private static final String FIND_CITY_BY_ID = COMMON_QUERY_PART
                                                  + " WHERE id=?;";

    /**
     * Query to find all cities in the database by country ID.
     */
    private static final String FIND_ALL_CITIES_BY_ID = COMMON_QUERY_PART
                                                        + " WHERE country_id=?";

    /**
     * Connection from a pool to MySQL database.
     */
    private Connection connection;

    /**
     * Constructs an instance with specific connection.
     *
     * @param connectionValue the provided connection.
     */
    public MysqlDestinationDao(final Connection connectionValue) {
        connection = connectionValue;
    }

    /**
     * Finds all possible destination in the database.
     *
     * @return the list of destinations.
     * @throws DaoException if failed to find all destination entities in the
     *                      data source.
     */
    @Override
    public List<Destination> findAllDestinations() throws DaoException {
        List<Destination> destinations = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    FIND_ALL_DESTINATIONS)) {
                while (resultSet.next()) {
                    int counter = 1;

                    Destination destination = new Destination(
                            resultSet.getInt(counter++));
                    destination.setCountryName(resultSet.getString(counter));

                    destinations.add(destination);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all destinations", e);
        }
        return destinations;
    }

    /**
     * Finds all cities by country ID in the database.
     *
     * @param countryId the provided country ID.
     * @return the list of destinations.
     * @throws DaoException if failed to find all city entities by country
     *                      ID in the database.
     */
    @Override
    public List<City> findAllCitiesById(final long countryId)
            throws DaoException {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_ALL_CITIES_BY_ID)) {
            statement.setLong(1, countryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    City city = getCity(resultSet);
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all cities by ID", e);
        }
        return cities;
    }

    /**
     * Finds city by ID in the data source if present.
     *
     * @param cityId the provided city ID.
     * @return the city entity if present.
     * @throws DaoException if failed to find city entity by ID in the
     *                      data source.
     */
    @Override
    public Optional<City> findCityById(final long cityId) throws DaoException {
        City city = null;
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_CITY_BY_ID)) {
            statement.setLong(1, cityId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = getCity(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find city by ID", e);
        }
        return Optional.ofNullable(city);
    }

    /**
     * Creates city entity from provided result data set.
     *
     * @param resultSet the provided result data set from database.
     * @return the result city entity.
     * @throws SQLException if an error occurs during operation execution.
     */
    private City getCity(final ResultSet resultSet) throws SQLException {
        City city = new City();
        int counter = 1;

        city.setId(resultSet.getInt(counter++));
        city.setCityName(resultSet.getString(counter++));
        city.setCountryID(resultSet.getInt(counter));

        return city;
    }
}

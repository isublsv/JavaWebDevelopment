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
     * Query to find all cities in the database by destination ID.
     */
    private static final String FIND_ALL_CITY_BY_ID =
            "SELECT c.id, c.country_id, c.name FROM city AS c "
            + "WHERE c.country_id=?";

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
     * Finds all cities by destination ID in the database.
     *
     * @param id the provided ID.
     * @return the list of destinations.
     * @throws DaoException if failed to find all city entities by destination
     *                      ID in the database.
     */
    @Override
    public List<City> findAllCitiesByID(final long id) throws DaoException {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ALL_CITY_BY_ID)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int counter = 1;

                    City city = new City(resultSet.getInt(counter++));
                    city.setCountryID(resultSet.getInt(counter++));
                    city.setCityName(resultSet.getString(counter));

                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all cities by ID", e);
        }
        return cities;
    }
}

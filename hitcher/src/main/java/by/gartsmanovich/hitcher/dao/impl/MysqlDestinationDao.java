package by.gartsmanovich.hitcher.dao.impl;

import by.gartsmanovich.hitcher.bean.Destination;
import by.gartsmanovich.hitcher.dao.DestinationDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.sql.Connection;
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
    private static final String FIND_ALL =
            "SELECT ci.id, ci.country_id, c.name, ci.name FROM city AS ci"
            + " INNER JOIN country AS c ON ci.country_id = c.id";

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
     * Finds all possible destination in the data source.
     *
     * @return the list of destinations.
     */
    @Override
    public List<Destination> findAll() throws DaoException {
        List<Destination> destinations = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    int counter = 1;

                    Destination destination = new Destination(
                            resultSet.getInt(counter++));
                    destination.setCountryId(resultSet.getInt(counter++));
                    destination.setCountryName(resultSet.getString(counter++));
                    destination.setCityName(resultSet.getString(counter));
                    destinations.add(destination);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all destinations", e);
        }
        return destinations;
    }
}

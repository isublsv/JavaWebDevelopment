package by.gartsmanovich.hitcher.dao.impl;

import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * @author Dmitry Gartsmanovich
 */
public class MysqlUserDao implements UserDao {

    /**
     * The logger for MysqlUserDao class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            MysqlUserDao.class);

    /**
     * Query to add a new user to the database.
     */
    private static final String INSERT_USER =
            "INSERT INTO hitcher_db.users (login, password, salt, "
            + "role) VALUES (?, ?, ?, ?);";

    /**
     * Common part of the find User query.
     */
    private static final String FIND_USER =
            "SELECT u.id, u.login, u.password, u.salt, u.role, h.surname,"
            + " h.name, h.patronymic, h.email, h.phone, h.registration_date,"
            + " h.address, h.music, h.communication, di.driving_licence_number,"
            + " di.car_model, di.car_color FROM hitchers AS h"
            + " INNER JOIN users u ON h.user_id = u.id"
            + " INNER JOIN driver_info di on u.id = di.user_id";

    /**
     * Query to find a user by ID value in the database.
     */
    private static final String FIND_BY_ID = FIND_USER + " WHERE h.user_id=?;";
    /**
     * Query to update data of selected user in the database.
     */
    private static final String UPDATE_USER =
            "UPDATE hitcher_db.hitchers AS h SET h.surname=?, "
            + "h.name=?, h.patronymic=?, h.email=?, h.phone=?, h.address=?, "
            + "h.music=?, h.communication=? WHERE h.user_id=?;";

    /**
     * Query to delete selected user from the database.
     */
    private static final String DELETE_USER = "DELETE FROM hitcher_db.users "
                                              + "WHERE users.id = ?;";

    /**
     * Query to find all users in the database.
     */
    private static final String FIND_ALL_USERS = FIND_USER + ";";

    /**
     * Query to find user by login and password.
     */
    private static final String FIND_USER_BY_LOGIN_AND_PASS =
            FIND_USER + " WHERE u.login=? AND u.password=? AND u.salt=?;";

    /**
     * Connection from a pool to MySQL database.
     */
    private WrapperConnection connection;

    /**
     * Constructs an instance with specific connection.
     *
     * @param connectionValue the provided connection.
     */
    public MysqlUserDao(final WrapperConnection connectionValue) {
        connection = connectionValue;
    }

    /**
     * Saves the provided entity in the database.
     *
     * @param entity the provided entity.
     * @throws DaoException if failed to create user in the database or
     * to get auto-incremented key.
     */
    @Override
    public void create(final User entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                INSERT_USER, RETURN_GENERATED_KEYS)) {
            int counter = 1;

            statement.setString(counter++, entity.getLogin());
            statement.setString(counter++, entity.getPassword());
            statement.setString(counter++, entity.getSalt());
            statement.setInt(counter, entity.getRole().ordinal());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setId(keys.getInt(1));
                } else {
                    String message = "Failed to get auto-incremented ID value"
                                     + " during INSERT USER operation.";
                    LOGGER.error(message);
                    throw new DaoException(message);
                }
            }
        } catch (SQLException e) {
            String message = "Failed to create user!";
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Finds entity in the database by provided ID if present.
     *
     * @param id the provided ID.
     * @return the entity value if present.
     * @throws DaoException if failed to find user by ID in the database.
     */
    @Override
    public Optional<User> findById(final long id) throws DaoException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_BY_ID)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        } catch (SQLException e) {
            String message = "Failed to find user by ID!";
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
        return Optional.ofNullable(user);
    }

    /**
     * Updates the provided entity in the database.
     *
     * @param entity the provided entity.
     * @throws DaoException if failed to update user in the database.
     */
    @Override
    public void update(final User entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                UPDATE_USER)) {
            int counter = 1;

            statement.setString(counter++, entity.getSurname());
            statement.setString(counter++, entity.getName());
            statement.setString(counter++, entity.getPatronymic());
            statement.setString(counter++, entity.getEmail());
            statement.setString(counter++, entity.getPhoneNumber());
            statement.setString(counter++, entity.getAddress());
            statement.setString(counter++, entity.getMusic());
            statement.setString(counter++, entity.getCommunication());

            statement.setLong(counter, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            String message = "Failed to update user!";
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Deletes the provided entity from the database.
     *
     * @param entity the provided entity.
     * @throws DaoException if failed to delete user from the database.
     */
    @Override
    public void delete(final User entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                DELETE_USER)) {

            statement.setLong(1, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            String message = "Failed to delete user!";
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
    }

    /**
     * Finds all entity in the database.
     *
     * @return the collection of found entities.
     * @throws DaoException if failed to find all users in the database.
     */
    @Override
    public Collection<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_ALL_USERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = getUser(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            String message = "Failed to find all users!";
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
        return users;
    }

    /**
     * Returns User entity from database if present.
     *
     * @param login    the login of the user.
     * @param password the password of the user.
     * @param salt     the password salt.
     * @return the user entity if present.
     * @throws DaoException if failed to find user in the database by login
     * and password.
     */
    @Override
    public Optional<User> findUserByLoginAndPass(final String login,
            final String password, final String salt) throws DaoException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_USER_BY_LOGIN_AND_PASS)) {

            int counter = 1;
            statement.setString(counter++, login);
            statement.setString(counter++, password);
            statement.setString(counter, salt);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        } catch (SQLException e) {
            String message = "Failed to find user by login and password!";
            LOGGER.error(message);
            throw new DaoException(message, e);
        }
        return Optional.ofNullable(user);
    }

    /**
     * Creates user entity from provided result set data.
     *
     * @param resultSet the provided result set data from database.
     * @return the result entity.
     * @throws SQLException if an error occurs during operation execution.
     */
    private User getUser(final ResultSet resultSet) throws SQLException {
        User user = new User();
        int counter = 1;

        user.setId(resultSet.getLong(counter++));
        user.setLogin(resultSet.getString(counter++));
        user.setPassword(resultSet.getString(counter++));
        user.setSalt(resultSet.getString(counter++));
        user.setRole(Role.values()[(resultSet.getInt(counter++))]);
        user.setSurname(resultSet.getString(counter++));
        user.setName(resultSet.getString(counter++));
        user.setPatronymic(resultSet.getString(counter++));
        user.setEmail(resultSet.getString(counter++));
        user.setPhoneNumber(resultSet.getString(counter++));
        user.setRegistrationDate(
                LocalDate.parse(resultSet.getString(counter++)));
        user.setAddress(resultSet.getString(counter++));
        user.setMusic(resultSet.getString(counter++));
        user.setCommunication(resultSet.getString(counter++));
        user.setDriverLicenseNumber(resultSet.getString(counter++));
        user.setCarModel(resultSet.getString(counter++));
        user.setCarColor(resultSet.getString(counter));

        return user;
    }
}

package by.gartsmanovich.hitcher.dao.impl;

import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.Status;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * The User DAO implementation is used to execute queries to the MySQL database.
 *
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
            "INSERT INTO hitcher_db.users (login, email, password, salt, "
            + "role, status) VALUES (?, ?, ?, ?, ?, ?);";

    /**
     * Common part of the find User query.
     */
    private static final String FIND_USER =
            "SELECT u.id, u.login, u.email, u.password, u.salt, u.role,"
            + " u.status, h.surname, h.name, h.patronymic, h.phone,"
            + " h.registration_date, h.address, h.music, h.communication,"
            + " di.driving_licence_number, di.car_model, di.car_color"
            + " FROM hitchers AS h INNER JOIN users AS u ON h.user_id = u.id"
            + " LEFT JOIN driver_info AS di ON u.id = di.user_id";

    /**
     * Query to find a user by ID value in the database.
     */
    private static final String FIND_BY_ID = FIND_USER + " WHERE h.user_id=?;";

    /**
     * Query to update data of selected user in the database.
     */
    private static final String UPDATE_USER =
            "UPDATE hitcher_db.hitchers AS h SET h.surname=?, "
            + "h.name=?, h.patronymic=?, h.phone=?, h.address=?, "
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
     * Query to find user by login.
     */
    private static final String FIND_USER_BY_LOGIN =
            FIND_USER + " WHERE u.login=?;";

    /**
     * Query to find user by email.
     */
    private static final String FIND_USER_BY_EMAIL =
            FIND_USER + " WHERE u.email=?;";

    /**
     * Connection from a pool to MySQL database.
     */
    private Connection connection;

    /**
     * Constructs an instance with specific connection.
     *
     * @param connectionValue the provided connection.
     */
    public MysqlUserDao(final Connection connectionValue) {
        connection = connectionValue;
    }

    /**
     * Saves the provided user entity to the database.
     *
     * @param entity the provided user entity.
     * @throws DaoException if failed to create user in the database or
     * to get auto-incremented key.
     */
    @Override
    public void create(final User entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                INSERT_USER, RETURN_GENERATED_KEYS)) {
            int counter = 1;

            statement.setString(counter++, entity.getLogin());
            statement.setString(counter++, entity.getEmail());
            statement.setString(counter++, entity.getPassword());
            statement.setString(counter++, entity.getSalt());
            statement.setInt(counter++, entity.getRole().ordinal());
            statement.setInt(counter, entity.getStatus().ordinal());

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
            throw new DaoException("Failed to create user!", e);
        }
    }

    /**
     * Finds user entity in the database by provided ID if present.
     *
     * @param id the provided ID.
     * @return the user value if present.
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
            throw new DaoException("Failed to find user by ID!", e);
        }
        return Optional.ofNullable(user);
    }

    /**
     * Updates the provided user entity in the database.
     *
     * @param entity the provided user entity.
     * @throws DaoException if failed to update user entity in the database.
     */
    @Override
    public void update(final User entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                UPDATE_USER)) {
            int counter = 1;

            statement.setString(counter++, entity.getSurname());
            statement.setString(counter++, entity.getName());
            statement.setString(counter++, entity.getPatronymic());
            statement.setString(counter++, entity.getPhoneNumber());
            statement.setString(counter++, entity.getAddress());
            statement.setString(counter++, entity.getMusic());
            statement.setString(counter++, entity.getCommunication());

            statement.setLong(counter, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update user!", e);
        }
    }

    /**
     * Deletes the provided user entity by ID from the database.
     *
     * @param id the provided user ID.
     * @throws DaoException if failed to delete user entity from the database.
     */
    @Override
    public void delete(final long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                DELETE_USER)) {

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to delete user!", e);
        }
    }

    /**
     * Finds all user entities in the database.
     *
     * @return the list of found user entities.
     * @throws DaoException if failed to find all users in the database.
     */
    @Override
    public List<User> findAll() throws DaoException {
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
            throw new DaoException("Failed to find all users!", e);
        }
        return users;
    }

    /**
     * Returns user entity from database if present.
     *
     * @param login    the login of the user.
     * @return the user entity if present.
     * @throws DaoException if failed to find user in the database by login
     * and password.
     */
    @Override
    public Optional<User> findUserByLogin(final String login)
            throws DaoException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_USER_BY_LOGIN)) {

            statement.setString(1, login);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find user by login", e);
        }
        return Optional.ofNullable(user);
    }

    /**
     * Returns user from the data source if present.
     *
     * @param email the user email.
     * @return the user entity if present.
     * @throws DaoException if failed to find user in the data source by email.
     */
    @Override
    public Optional<User> findUserByEmail(final String email) throws
            DaoException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                FIND_USER_BY_EMAIL)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find user by email", e);
        }
        return Optional.ofNullable(user);
    }

    /**
     * Creates user entity from provided result set data.
     *
     * @param resultSet the provided result set data from database.
     * @return the result user entity.
     * @throws SQLException if an error occurs during operation execution.
     */
    private User getUser(final ResultSet resultSet) throws SQLException {
        User user = new User();
        int counter = 1;

        user.setId(resultSet.getLong(counter++));
        user.setLogin(resultSet.getString(counter++));
        user.setEmail(resultSet.getString(counter++));
        user.setPassword(resultSet.getString(counter++));
        user.setSalt(resultSet.getString(counter++));
        user.setRole(Role.values()[(resultSet.getInt(counter++))]);
        user.setStatus(Status.values()[(resultSet.getInt(counter++))]);
        user.setSurname(resultSet.getString(counter++));
        user.setName(resultSet.getString(counter++));
        user.setPatronymic(resultSet.getString(counter++));
        user.setPhoneNumber(resultSet.getString(counter++));
        user.setRegistrationDate((resultSet.getDate(counter++).toLocalDate()));
        user.setAddress(resultSet.getString(counter++));
        user.setMusic(resultSet.getString(counter++));
        user.setCommunication(resultSet.getString(counter++));
        user.setDriverLicenseNumber(resultSet.getString(counter++));
        user.setCarModel(resultSet.getString(counter++));
        user.setCarColor(resultSet.getString(counter));

        return user;
    }
}

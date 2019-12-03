package by.gartsmanovich.hitcher.dao.impl;

import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.Status;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            + "role, status, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?);";

    /**
     * Common part of the find User query with full data.
     */
    private static final String FIND_FULL_DATA_USER =
            "SELECT u.id, u.login, u.email, u.password, u.salt, u.role,"
            + " u.status, u.registration_date, h.surname, h.name, h.patronymic,"
            + " h.phone, h.address, h.music_id, h.communication_id,"
            + " di.driving_licence_number, di.car_model, di.car_color"
            + " FROM users AS u LEFT JOIN hitchers AS h ON u.id = h.user_id"
            + " LEFT JOIN driver_info AS di ON u.id = di.user_id";

    /**
     * Common part of the find User query with compact data.
     */
    private static final String FIND_COMPACT_DATA_USER =
            "SELECT u.id, u.login, u.email, u.password, u.salt, u.role,"
            + " u.status FROM users AS u";

    /**
     * Query to find a user by ID value in the database.
     */
    private static final String FIND_BY_ID = FIND_FULL_DATA_USER
                                             + " WHERE u.id=?;";

    /**
     * Query to update data of selected user in the database.
     */
    private static final String UPDATE_USER =
            "UPDATE users AS u INNER JOIN"
            + " hitchers AS h ON u.id = h.user_id SET "
            + " u.email=?, h.surname=?, h.name=?, h.patronymic=?, h.phone=?,"
            + " h.address=?, h.music_id=?, h.communication_id=? WHERE u.id=?;";

    /**
     * Query to update user password and salt in the database.
     */
    private static final String UPDATE_USER_PASSWORD =
            "UPDATE users AS u SET u.password=?, u.salt=? WHERE u.id=?";

    /**
     * Query to update user driver info in the database.
     */
    private static final String UPDATE_DRIVER_INFO =
            "UPDATE users AS u INNER JOIN driver_info AS di ON"
            + " u.id = di.user_id SET di.driving_licence_number=?,"
            + " di.car_model=?, di.car_color=? WHERE u.id=?;";

    /**
     * Query to create user driver info in the database.
     */
    private static final String CREATE_DRIVER_INFO =
            "INSERT INTO hitcher_db.driver_info"
            + " (user_id, driving_licence_number, car_model, car_color)"
            + " VALUES (?, ?, ?, ?)";

    /**
     * Query to delete selected user from the database.
     */
    private static final String DELETE_USER = "DELETE FROM hitcher_db.users "
                                              + "WHERE users.id=?;";

    /**
     * Query to find all users in the database.
     */
    private static final String FIND_ALL_USERS = FIND_COMPACT_DATA_USER + ";";

    /**
     * Query to find user by login.
     */
    private static final String FIND_USER_BY_LOGIN =
            FIND_COMPACT_DATA_USER + " WHERE u.login=?;";

    /**
     * Query to find user by email.
     */
    private static final String FIND_USER_BY_EMAIL =
            FIND_COMPACT_DATA_USER + " WHERE u.email=?;";

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
            statement.setInt(counter++, entity.getStatus().ordinal());
            statement.setDate(counter,
                              Date.valueOf(entity.getRegistrationDate()));

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
            throw new DaoException("Failed to create user.", e);
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
                    user = getFullUserData(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find user by ID", e);
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

            statement.setString(counter++, entity.getEmail());
            statement.setString(counter++, entity.getSurname());
            statement.setString(counter++, entity.getName());
            statement.setString(counter++, entity.getPatronymic());
            statement.setString(counter++, entity.getPhoneNumber());
            statement.setString(counter++, entity.getAddress());
            statement.setInt(counter++, entity.getMusic());
            statement.setInt(counter++, entity.getCommunication());

            statement.setLong(counter, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update user.", e);
        }
    }

    /**
     * Updates password and salt of the user entity in the database.
     *
     * @param user the provided user entity.
     * @throws DaoException if failed to update user password in the database.
     */
    @Override
    public void updatePassword(final User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                UPDATE_USER_PASSWORD)) {
            int counter = 1;

            statement.setString(counter++, user.getPassword());
            statement.setString(counter++, user.getSalt());
            statement.setLong(counter, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update user password.", e);
        }
    }

    /**
     * Updates a driver information of the user entity in the data source.
     *
     * @param user the provided user entity.
     * @throws DaoException if failed to update user driver information in the
     *                      data source.
     */
    @Override
    public void updateDriverInfo(final User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                UPDATE_DRIVER_INFO)) {
            int counter = 1;

            statement.setString(counter++, user.getDriverLicenseNumber());
            statement.setString(counter++, user.getCarModel());
            statement.setString(counter++, user.getCarColor());
            statement.setLong(counter, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update user driver info.", e);
        }
    }

    /**
     * Adds an user driver information to the data source.
     *
     * @param user the provided user entity.
     * @throws DaoException if failed to add user driver information to the
     *                      data source.
     */
    @Override
    public void addDriverInfo(final User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(
                CREATE_DRIVER_INFO)) {
            int counter = 1;

            statement.setLong(counter++, user.getId());
            statement.setString(counter++, user.getDriverLicenseNumber());
            statement.setString(counter++, user.getCarModel());
            statement.setString(counter, user.getCarColor());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to create user driver info.", e);
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
            throw new DaoException("Failed to delete user.", e);
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
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS)) {
                while (resultSet.next()) {
                    User user = getCompactUserData(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all users.", e);
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
                    user = getCompactUserData(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find user by login.", e);
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
                    user = getFullUserData(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find user by email.", e);
        }
        return Optional.ofNullable(user);
    }

    /**
     * Creates full version of user entity from provided result set data.
     *
     * @param resultSet the provided result set data from database.
     * @return the result user entity.
     * @throws SQLException if an error occurs during operation execution.
     */
    private User getFullUserData(final ResultSet resultSet)
            throws SQLException {
        User user = new User();
        int counter = 1;

        user.setId(resultSet.getLong(counter++));
        user.setLogin(resultSet.getString(counter++));
        user.setEmail(resultSet.getString(counter++));
        user.setPassword(resultSet.getString(counter++));
        user.setSalt(resultSet.getString(counter++));
        user.setRole(Role.values()[(resultSet.getInt(counter++))]);
        user.setStatus(Status.values()[(resultSet.getInt(counter++))]);
        user.setRegistrationDate((resultSet.getDate(counter++).toLocalDate()));
        user.setSurname(resultSet.getString(counter++));
        user.setName(resultSet.getString(counter++));
        user.setPatronymic(resultSet.getString(counter++));
        user.setPhoneNumber(resultSet.getString(counter++));
        user.setAddress(resultSet.getString(counter++));
        user.setMusic(resultSet.getInt(counter++));
        user.setCommunication(resultSet.getInt(counter++));
        user.setDriverLicenseNumber(resultSet.getString(counter++));
        user.setCarModel(resultSet.getString(counter++));
        user.setCarColor(resultSet.getString(counter));

        return user;
    }

    /**
     * Creates compact version of user entity from provided result set data.
     *
     * @param resultSet the provided result set data from database.
     * @return the result user entity.
     * @throws SQLException if an error occurs during operation execution.
     */
    private User getCompactUserData(final ResultSet resultSet)
            throws SQLException {
        User user = new User();
        int counter = 1;

        user.setId(resultSet.getLong(counter++));
        user.setLogin(resultSet.getString(counter++));
        user.setEmail(resultSet.getString(counter++));
        user.setPassword(resultSet.getString(counter++));
        user.setSalt(resultSet.getString(counter++));
        user.setRole(Role.values()[(resultSet.getInt(counter++))]);
        user.setStatus(Status.values()[(resultSet.getInt(counter))]);

        return user;
    }
}

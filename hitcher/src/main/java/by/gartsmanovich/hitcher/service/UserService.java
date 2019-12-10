package by.gartsmanovich.hitcher.service;

import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

import java.util.List;

/**
 * The interface is used to determine the common structure of User Service
 * Layer class.
 *
 * @author Dmitry Gartsmanovich
 */
public interface UserService {

    /**
     * Creates and saves the provided user entity by given parameters
     * to the data source.
     *
     * @param login the provided entity login value
     * @param email the provided entity email value
     * @param pass  the provided entity password value
     * @return the new user entity.
     * @throws ServiceException if failed to create user entity in the data
     *                          source.
     */
    User save(String login, String email, String pass) throws ServiceException;

    /**
     * Updates personal user information in the data source.
     *
     * @param user the provided user entity with a new personal information.
     * @return the updated user entity.
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    User updatePersonalData(User user) throws ServiceException;

    /**
     * Updates music and communication user preferences in the data source.
     *
     * @param id            the provided user ID.
     * @param music         the provided music preferences.
     * @param communication the provided communication preferences.
     * @return the updated user entity.
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    User updatePreferences(long id, String music, String communication) throws
            ServiceException;

    /**
     * Updates user driver information in the data source.
     *
     * @param id       the provided user ID.
     * @param license  the provided driver license.
     * @param carModel the provided car model.
     * @param carColor the provided car color.
     * @return the updated user entity.
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    User updateDriverInfo(long id, String license, String carModel,
            String carColor) throws ServiceException;

    /**
     * Updates user email in the data source.
     *
     * @param id    the provided user ID.
     * @param email the provided email value.
     * @return the updated user entity.
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    User updateEmail(long id, String email) throws ServiceException;

    /**
     * Updates user password in the data source.
     *
     * @param id          the provided user ID.
     * @param currentPass the provided current password value.
     * @param newPass     the provided new password value.
     * @return the updated user entity.
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    User updatePassword(long id, String currentPass, String newPass) throws
            ServiceException;

    /**
     * Finds user entity by provided ID value.
     *
     * @param id the provided user ID.
     * @return the user entity.
     * @throws ServiceException if failed to find user entity in the data
     *                          source.
     */
    User findByID(long id) throws ServiceException;

    /**
     * Deletes user entity by ID from the data source.
     *
     * @param id the provided user ID.
     * @throws ServiceException if failed to delete user entity from the
     *                          data source.
     */
    void delete(long id) throws ServiceException;

    /**
     * Finds all users in the data source.
     *
     * @return the user list.
     * @throws ServiceException if failed to find users in the data source.
     */
    List<User> findAll() throws ServiceException;

    /**
     * Finds user entity by login and password value.
     *
     * @param login    the provided user login.
     * @param password the provided user password.
     * @return the user entity.
     * @throws ServiceException if failed to find user entity in the
     *                          data source.
     */
    User findByLoginAndPassword(String login, String password) throws
            ServiceException;

    /**
     * Finds user entity by provided id in the data source.
     *
     * @param id the provided user ID.
     * @return the user entity.
     * @throws ServiceException if failed to find user entity in the data
     *                          source.
     */
    User findUserProfileByID(String id) throws ServiceException;
}

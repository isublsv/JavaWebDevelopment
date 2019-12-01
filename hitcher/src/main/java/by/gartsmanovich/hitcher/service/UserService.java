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
     * @return the new user value
     * @throws ServiceException if failed to create user entity in the data
     *                          source.
     */
    User save(String login, String email, String pass) throws ServiceException;

    /**
     * Updates personal user information in the data source.
     *
     * @param user the provided user entity with a new personal information.
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    void updatePersonalData(User user) throws ServiceException;

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
}

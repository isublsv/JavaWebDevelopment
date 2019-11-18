package by.gartsmanovich.hitcher.service;

import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface is used to determine the common structure of User Service
 * Layer class.
 *
 * @author Dmitry Gartsmanovich
 */
public interface UserService {

    /**
     * Saves the provided user entity to the data source.
     *
     * @param user the provided entity
     * @throws ServiceException if failed to create user.
     */
    void save(User user) throws ServiceException;

    /**
     * Finds user entity by provided ID value.
     *
     * @param id the provided user ID.
     * @return the user entity if present.
     * @throws ServiceException if failed to find user entity.
     */
    Optional<User> findByID(long id) throws ServiceException;

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
     * Finds user entity by login and password value if present.
     *
     * @param login    the provided user login.
     * @param password the provided user password.
     * @return the user entity if present.
     * @throws ServiceException if failed to find user entity in the
     *                          data source.
     */
    Optional<User> findByLoginAndPassword(String login, String password) throws
            ServiceException;
}

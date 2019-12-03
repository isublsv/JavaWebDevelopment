package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface describes the common structure for user type DAO.
 *
 * @author Dmitry Gartsmanovich
 */
public interface UserDao extends AbstractDao<User> {

    /**
     * Updates password and salt of the user entity in the data source.
     *
     * @param user the provided user entity.
     * @throws DaoException if failed to update user password in the
     *                      data source.
     */
    void updatePassword(User user) throws DaoException;

    /**
     * Updates a driver information of the user entity in the data source.
     *
     * @param user the provided user entity.
     * @throws DaoException if failed to update user driver information in the
     *                      data source.
     */
    void updateDriverInfo(User user) throws DaoException;

    /**
     * Finds user by login in the data source if present.
     *
     * @param login the user login.
     * @return the user entity if present.
     * @throws DaoException if failed to find user in the data source by login.
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Finds user by email in the data source if present.
     *
     * @param email the user email.
     * @return the user entity if present.
     * @throws DaoException if failed to find user in the data source by email.
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Finds all users in the data source.
     *
     * @return the list of found users.
     * @throws DaoException if failed to find all users in the data source.
     */
    List<User> findAll() throws DaoException;

}

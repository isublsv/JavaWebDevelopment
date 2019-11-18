package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * @author Dmitry Gartsmanovich
 */
public interface UserDao extends AbstractDao<User> {

    /**
     * Returns user from the data source if present.
     *
     * @param login    the login of the user.
     * @return the user entity if present.
     * @throws DaoException if failed to find user in the data source by login
     * and password.
     */
    Optional<User> findUserByLogin(String login)
            throws DaoException;


    /**
     * Finds all users in the data source.
     *
     * @return the list of found users.
     * @throws DaoException if failed to find all users in the data source.
     */
    List<User> findAll() throws DaoException;
}

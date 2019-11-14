package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Dmitry Gartsmanovich
 */
public interface UserDao extends AbstractDao<User> {

    /**
     * Returns User entity from database if present.
     *
     * @param login    the login of the user.
     * @param password the password of the user.
     * @param salt     the password salt.
     * @return the user entity if present
     */
    Optional<User> findUserByLoginAndPass(String login, String password,
            String salt) throws SQLException, DaoException;
}

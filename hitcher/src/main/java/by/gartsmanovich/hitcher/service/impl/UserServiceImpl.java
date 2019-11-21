package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.util.PasswordUtils;

import java.util.List;
import java.util.Optional;

/**
 * The realisation of User Service interface is used to describe of application
 * functionality for the user entity.
 *
 * @author Dmitry Gartsmanovich
 */
public class UserServiceImpl implements UserService {

    /**
     * Represents the transaction entity.
     */
    private Transaction transaction;

    /**
     * Constructs the User Service realisation instance with provided
     * transaction value.
     *
     * @param transactionValue the provided transaction.
     */
    public UserServiceImpl(final Transaction transactionValue) {
        this.transaction = transactionValue;
    }

    /**
     * Saves the provided user entity to the data source.
     *
     * @param user the provided entity
     * @throws ServiceException if failed to create user.
     */
    @Override
    public void save(final User user) throws ServiceException {
        UserDao dao = transaction.getUserDao();
        try {
            dao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds user entity by provided ID value.
     *
     * @param id the provided user ID.
     * @return the user entity.
     * @throws ServiceException if failed to find user entity.
     */
    @Override
    public User findByID(final long id) throws ServiceException {
        UserDao dao = transaction.getUserDao();
        try {
            Optional<User> user = dao.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new ServiceException("User not found!");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes user entity by ID from the data source.
     *
     * @param id the provided user ID.
     * @throws ServiceException if failed to delete user entity from the
     *                          data source.
     */
    @Override
    public void delete(final long id) throws ServiceException {
        UserDao dao = transaction.getUserDao();
        try {
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds all users in the data source.
     *
     * @return the user list.
     * @throws ServiceException if failed to find users in the data source.
     */
    @Override
    public List<User> findAll() throws ServiceException {
        UserDao dao = transaction.getUserDao();
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds user entity by login and password value.
     *
     * @param login    the provided user login.
     * @param password the provided user password.
     * @return the user entity.
     * @throws ServiceException if failed to find user entity in the
     *                          data source.
     */
    @Override
    public User findByLoginAndPassword(final String login,
            final String password) throws ServiceException {
        UserDao dao = transaction.getUserDao();
        try {
            Optional<User> optionalUser = dao.findUserByLogin(login);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String savedPassword = user.getPassword();
                String salt = user.getSalt();
                if (PasswordUtils.verifyUserPassword(
                        password, savedPassword, salt)) {
                    return optionalUser.get();
                } else {
                    throw new ServiceException("Wrong password!");
                }
            } else {
                throw new ServiceException("User does not exist!");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

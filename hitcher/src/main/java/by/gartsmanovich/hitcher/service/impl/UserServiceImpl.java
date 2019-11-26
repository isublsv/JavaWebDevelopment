package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.Status;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.util.PasswordUtils;
import by.gartsmanovich.hitcher.service.validator.ServiceValidator;

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
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private ServiceValidator validator;

    /**
     * Constructs the User Service realisation instance with provided
     * transaction value.
     *
     * @param transactionValue the provided transaction.
     */
    public UserServiceImpl(final Transaction transactionValue) {
        this.transaction = transactionValue;
        this.validator = new ServiceValidator();
    }

    /**
     * Creates and saves the provided user entity by given parameters
     * to the data source.
     *
     * @param login the provided entity login value
     * @param email the provided entity email value
     * @param pass the provided entity password value
     * @return the new user value
     * @throws ServiceException if failed to create user.
     */
    @Override
    public User save(final String login, final String email,
            final String pass) throws ServiceException {

        UserDao dao = transaction.getUserDao();
        User user;
        try {
            if (!validator.isValidLogin(login)) {
                String message = "Username value is not valid";
                throw new ServiceException(message);
            }
            if (!validator.isValidEmail(email)) {
                String message = "Email value is not valid";
                throw new ServiceException(message);
            }
            if (dao.findUserByLogin(login).isPresent()) {
                String message = "User with that username is already exists";
                throw new ServiceException(message);
            }
            if (dao.findUserByEmail(email).isPresent()) {
                String message = "User with that email is already exists";
                throw new ServiceException(message);
            }
            user = new User();
            user.setLogin(login);
            user.setEmail(email);

            String salt = PasswordUtils.getSalt();
            String newSecurePass = PasswordUtils
                    .generateSecurePassword(pass, salt);

            user.setPassword(newSecurePass);
            user.setSalt(salt);
            user.setRole(Role.USER);
            user.setStatus(Status.ACTIVE);
            dao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
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
     * Deletes user entity by ID from the database.
     *
     * @param id the provided user ID.
     * @throws ServiceException if failed to delete user entity from the
     *                          database.
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
     * Finds all users in the database.
     *
     * @return the user list.
     * @throws ServiceException if failed to find users in the database.
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
     *                          database.
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

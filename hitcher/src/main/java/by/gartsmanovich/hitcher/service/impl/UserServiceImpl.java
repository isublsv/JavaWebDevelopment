package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.Status;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.validator.ServiceValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.EMAIL_EXISTS;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_ADDRESS;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_CURRENT_PASS;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_DRIVER_INFO;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_EMAIL;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_LOGIN;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_NAME;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_PASS;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_PATRONYMIC;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_PHONE_NUMBER;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_PREFERENCES;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_SURNAME;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_VALUES;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.SQL_ERROR;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.USER_DOES_NOT_EXIST;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.USER_EXISTS;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.USER_NOT_FOUND;
import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.WRONG_LOGIN_OR_PASS;
import static by.gartsmanovich.hitcher.service.util.PasswordUtils.generateSecurePassword;
import static by.gartsmanovich.hitcher.service.util.PasswordUtils.getSalt;
import static by.gartsmanovich.hitcher.service.util.PasswordUtils.verifyUserPassword;

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
     * @param pass  the provided entity password value
     * @return the new user value
     * @throws ServiceException if failed to create user entity in the data
     *                          source.
     */
    @Override
    public User save(final String login, final String email,
            final String pass) throws ServiceException {

        try {
            if (!validator.isValidLogin(login)) {
                throw new ServiceException(INVALID_LOGIN);
            }
            if (!validator.isValidEmail(email)) {
                throw new ServiceException(INVALID_EMAIL);
            }

            UserDao dao = transaction.getUserDao();

            if (dao.findUserByLogin(login).isPresent()) {
                throw new ServiceException(USER_EXISTS);
            }
            if (dao.findUserByEmail(email).isPresent()) {
                throw new ServiceException(EMAIL_EXISTS);
            }

            User user = new User();
            user.setLogin(login);
            user.setEmail(email);

            String salt = getSalt();
            String newSecurePass = generateSecurePassword(pass, salt);

            user.setPassword(newSecurePass);
            user.setSalt(salt);
            user.setRole(Role.USER);
            user.setStatus(Status.ACTIVE);
            user.setRegistrationDate(LocalDate.now());

            return clearPassword(dao.create(user));
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Updates personal user information in the data source.
     *
     * @param user the provided user entity with a new personal information.
     * @return the updated user value
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    @Override
    public User updatePersonalData(final User user) throws ServiceException {

        try {
            if (!validator.isValidName(user.getName())) {
                throw new ServiceException(INVALID_NAME);
            }
            if (!validator.isValidSurname(user.getSurname())) {
                throw new ServiceException(INVALID_SURNAME);
            }
            if (!validator.isValidPatronymic(user.getPatronymic())) {
                throw new ServiceException(INVALID_PATRONYMIC);
            }
            if (!validator.isValidPhone(user.getPhoneNumber())) {
                throw new ServiceException(INVALID_PHONE_NUMBER);
            }
            if (!validator.isValidAddress(user.getAddress())) {
                throw new ServiceException(INVALID_ADDRESS);
            }

            UserDao dao = transaction.getUserDao();
            Optional<User> optionalUser = dao.findById(user.getId());
            if (optionalUser.isPresent()) {
                User userToUpdate = optionalUser.get();

                userToUpdate.setName(user.getName());
                userToUpdate.setSurname(user.getSurname());
                userToUpdate.setPatronymic(user.getPatronymic());
                userToUpdate.setPhoneNumber(user.getPhoneNumber());
                userToUpdate.setAddress(user.getAddress());

                dao.update(userToUpdate);

                return userToUpdate;
            } else {
                throw new ServiceException(USER_NOT_FOUND);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Updates music and communication user preferences in the data source.
     *
     *
     * @param id the provided user ID.
     * @param music the provided music preferences.
     * @param communication the provided communication preferences.
     * @return the updated user value
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    @Override
    public User updatePreferences(final long id, final String music,
            final String communication) throws ServiceException {

        try {
            if (!validator.isValidPreferences(music)
                || !validator.isValidPreferences(communication)) {
                throw new ServiceException(INVALID_PREFERENCES);
            }

            UserDao dao = transaction.getUserDao();
            Optional<User> optionalUser = dao.findById(id);
            if (optionalUser.isPresent()) {
                User userToUpdate = optionalUser.get();

                userToUpdate.setMusic(Integer.parseInt(music));
                userToUpdate.setCommunication(Integer.parseInt(communication));

                dao.update(userToUpdate);

                return userToUpdate;
            } else {
                throw new ServiceException(USER_NOT_FOUND);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Updates user email in the data source.
     *
     * @param id    the provided user ID.
     * @param email the provided email value.
     * @return the updated user entity.
     * @throws ServiceException if failed to update user entity in the data
     *                          source.
     */
    @Override
    public User updateEmail(final long id, final String email) throws
            ServiceException {

        try {
            if (!validator.isValidEmail(email)) {
                throw new ServiceException(INVALID_EMAIL);
            }

            UserDao dao = transaction.getUserDao();
            Optional<User> optionalUser = dao.findById(id);
            if (optionalUser.isPresent()) {
                User userToUpdate = optionalUser.get();

                userToUpdate.setEmail(email);

                dao.update(userToUpdate);

                return userToUpdate;
            } else {
                throw new ServiceException(USER_NOT_FOUND);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

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
    @Override
    public User updatePassword(final long id, final String currentPass,
                              final String newPass) throws
            ServiceException {

        try {
            if (!validator.isValidPassword(newPass)) {
                throw new ServiceException(INVALID_PASS);
            }

            UserDao dao = transaction.getUserDao();
            Optional<User> optionalUser = dao.findById(id);
            if (optionalUser.isPresent()) {
                User userToUpdate = optionalUser.get();

                String currentSalt = userToUpdate.getSalt();
                String currentDbPass = userToUpdate.getPassword();

                if (verifyUserPassword(currentPass, currentDbPass,
                                       currentSalt)) {
                    String salt = getSalt();
                    String newPassHash = generateSecurePassword(newPass, salt);

                    userToUpdate.setPassword(newPassHash);
                    userToUpdate.setSalt(salt);

                    dao.updatePassword(userToUpdate);
                } else {
                    throw new ServiceException(INVALID_CURRENT_PASS);
                }

                return clearPassword(userToUpdate);
            } else {
                throw new ServiceException(SQL_ERROR);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

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
    @Override
    public User updateDriverInfo(final long id, final String license,
            final String carModel, final String carColor) throws
            ServiceException {

        try {
            if (!validator.isValidLicense(license)
                || !validator.isValidValues(carColor, carModel)) {
                throw new ServiceException(INVALID_DRIVER_INFO);
            }

            UserDao dao = transaction.getUserDao();
            Optional<User> optionalUser = dao.findById(id);
            if (optionalUser.isPresent()) {
                User userToUpdate = optionalUser.get();

                boolean isUpdatable = userToUpdate
                                              .getDriverLicenseNumber() != null;

                userToUpdate.setDriverLicenseNumber(license);
                userToUpdate.setCarModel(carModel);
                userToUpdate.setCarColor(carColor);

                if (isUpdatable) {
                    dao.updateDriverInfo(userToUpdate);
                } else {
                    dao.addDriverInfo(userToUpdate);
                }
                return userToUpdate;
            } else {
                throw new ServiceException(USER_NOT_FOUND);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
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
                throw new ServiceException(USER_NOT_FOUND);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
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
            throw new ServiceException(e, SQL_ERROR);
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
            throw new ServiceException(e, SQL_ERROR);
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
                if (verifyUserPassword(
                        password, savedPassword, salt)) {
                    return clearPassword(user);
                } else {
                    throw new ServiceException(WRONG_LOGIN_OR_PASS);
                }
            } else {
                throw new ServiceException(USER_DOES_NOT_EXIST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Finds user entity by provided id in the data source.
     *
     * @param id the provided user ID.
     * @return the user entity.
     * @throws ServiceException if failed to find user entity in the data
     *                          source.
     */
    @Override
    public User findUserProfileByID(final String id) throws ServiceException {

        if (!validator.isValidNumbers(id)) {
            throw new ServiceException(INVALID_VALUES);
        }

        return findByID(Long.parseLong(id));
    }

    /**
     * Clears password and salt values to store user entity to the session.
     *
     * @param user the provided user entity.
     * @return the user entity.
     */
    private User clearPassword(final User user) {
        //clear pass and salt to store user in the session
        user.setPassword(null);
        user.setSalt(null);

        return user;
    }
}

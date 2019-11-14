package by.gartsmanovich.hitcher.dao.factory;

import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.AbstractDao;
import by.gartsmanovich.hitcher.dao.mysql.MysqlUserDao;

/**
 * Class DAO factory used to create the different kinds of data access objects
 * depending on the entity type.
 *
 * @author Dmitry Gartsmanovich
 */
public final class DaoFactory {

    /**
     * Non-lazy initialisation of DAO Factory class.
     */
    private static final DaoFactory INSTANCE = new DaoFactory();

    /**
     * Provides the access to User DAO class methods.
     */
    private final AbstractDao<User> userDao = new MysqlUserDao();

    /**
     * Private constructor. Forbids the explicit object creation.
     */
    private DaoFactory() {
    }

    /**
     * Global point for access to factory methods.
     *
     * @return the instance of Data Access Object factory.
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the implementation of the User DAO interface.
     * An instance provide the access to Data Access Object application
     * layer methods.
     *
     * @return an instance of User DAO implementation.
     */
    public AbstractDao getUserDao() {
        return userDao;
    }
}

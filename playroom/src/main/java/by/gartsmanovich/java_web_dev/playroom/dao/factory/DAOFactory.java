package by.gartsmanovich.java_web_dev.playroom.dao.factory;

import by.gartsmanovich.java_web_dev.playroom.dao.ToyDAO;
import by.gartsmanovich.java_web_dev.playroom.dao.impl.ToyDAOImpl;

public final class DAOFactory {

    /**
     * The factory instance will be created at the start of the execution of
     * DAOFactory class.
     */
    private static final DAOFactory INSTANCE = new DAOFactory();

    /**
     * The variable provides the access to ToyDAO class methods.
     */
    private final ToyDAO toyDAO = new ToyDAOImpl();

    private DAOFactory() {

    }

    /**
     * Global point for access to factory methods.
     *
     * @return the instance of Data Access Object factory.
     */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the implementation of the ToyDAO interface.
     * An instance provide the access to Data Access Object application
     * layer methods.
     *
     * @return an instance of ToyDAO implementation.
     */
    public ToyDAO getToyDAO() {
        return toyDAO;
    }
}
